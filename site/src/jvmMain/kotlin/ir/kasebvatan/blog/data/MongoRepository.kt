package ir.kasebvatan.blog.data

import ir.kasebvatan.blog.models.User

interface MongoRepository {
    suspend fun checkUser(user: User): User?
    suspend fun checkUserId(id: String): Boolean
}