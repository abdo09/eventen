package net.tinat.group.eventen.data.client.network


sealed class Resource<out T: Any>{
    class ShowProgress
    class HideProgress
    class OnSuccess<out T: Any>(val data: T) : Resource<T>()
    class OnError(val exception: Throwable): Resource<Nothing>()
}