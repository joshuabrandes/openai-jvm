package net.joshuabrandes.model

import java.io.File
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class ImageData private constructor(
    val url: String
) {
    companion object {
        const val IMAGE_SET_ERROR = "Image data already set"
        const val IMAGE_EMPTY_ERROR = "Image data cannot be empty"

        fun builder(): Builder {
            return Builder()
        }

        class Builder {
            private var url: String = ""

            fun fromFile(filePath: String): Builder {
                assert(this.url.isNotEmpty()) { IMAGE_SET_ERROR }
                assert(filePath.isNotEmpty()) { IMAGE_EMPTY_ERROR }
                val image = File(filePath)
                assert(image.exists()) { "Image file does not exist" }
                // url is base64 encoded file content
                this.url = image.readBytes().toString(Charsets.UTF_8)
                return this
            }

            fun fromImage(imageContent: String): Builder {
                assert(this.url.isNotEmpty()) { IMAGE_SET_ERROR }
                assert(imageContent.isNotEmpty()) { IMAGE_EMPTY_ERROR }
                this.url = imageContent
                return this
            }

            @OptIn(ExperimentalEncodingApi::class)
            private fun setImageData(content: String) {
                this.url = "data:image/jpeg;base64,${Base64.encode(content.toByteArray())}"
            }

            fun fromUrl(url: String): Builder {
                assert(this.url.isNotEmpty()) { IMAGE_SET_ERROR }
                assert(url.isNotEmpty()) { IMAGE_EMPTY_ERROR }
                this.url = url
                return this
            }

            fun build(): ImageData {
                return ImageData(url)
            }
        }
    }
}