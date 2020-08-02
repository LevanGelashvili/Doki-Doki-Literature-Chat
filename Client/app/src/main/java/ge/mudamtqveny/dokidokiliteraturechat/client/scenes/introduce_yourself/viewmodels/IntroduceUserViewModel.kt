package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself.viewmodels

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserLoginEntity


data class IntroduceUserViewModel (
    val nickname: String,
    val job: String,
    val picture: String
) {
    fun toUserLoginEntity(): UserLoginEntity {
        return UserLoginEntity(nickname, job, picture)
    }
}