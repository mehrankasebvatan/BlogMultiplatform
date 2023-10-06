package ir.kasebvatan.blog.data

import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import ir.kasebvatan.blog.models.User
import ir.kasebvatan.blog.util.Constant
import kotlinx.coroutines.reactive.awaitFirst
import org.litote.kmongo.and
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.reactivestreams.getCollection

@InitApi
fun initMongoDb(context: InitApiContext) {
    System.setProperty(
        "org.litote.mongo.test.mapping.service",
        "org.litote.kmongo.serialization.SerializationClassMappingTypeService"
    )
    context.data.add(MongoDB(context))
}


class MongoDB(private val context: InitApiContext) : MongoRepository {
    private val client = KMongo.createClient()
    private val database = client.getDatabase(Constant.DATABASE_NAME)
    private val userCollection = database.getCollection<User>()
    override suspend fun checkUser(user: User): User? {
        return try {
            userCollection
                .find(
                    and(
                        User::username eq user.username,
                        User::password eq user.password
                    )
                ).awaitFirst()
        } catch (e: Exception) {
            context.logger.error(e.message.toString())
            null
        }
    }

    override suspend fun checkUserId(id: String): Boolean {
        return try {
            val documentCount = userCollection.countDocuments(User::id eq id).awaitFirst()
            documentCount > 0
        } catch (e: Exception) {
            context.logger.error(e.message.toString())
            false
        }
    }

}