
package ge.mudamtqveny.dokidokiliteraturechat.client.core.entities

import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.viewmodels.ToolbarUserViewModel
import ge.mudamtqveny.dokidokiliteraturechat.client.utils.base64ToBitmap
import java.io.Serializable

data class UserEntity (
    val id: Long,
    val name: String,
    val job: String,
    val picture: String
): Serializable {

    fun toToolbarViewModel(): ToolbarUserViewModel {
        return ToolbarUserViewModel(name, job, base64ToBitmap(picture))
    }
}

data class UserIdEntity (
    val id: Long
): Serializable

data class UserLoginEntity (
    val name: String,
    val job: String,
    val picture: String
)

data class UserSearchEntity (
    val userId: Long,
    val word: String
)
