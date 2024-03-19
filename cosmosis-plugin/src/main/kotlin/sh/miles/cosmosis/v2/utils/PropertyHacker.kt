package sh.miles.cosmosis.v2.utils

import org.gradle.api.internal.provider.AbstractProperty
import org.gradle.api.internal.provider.PropertyFactory
import org.gradle.api.internal.provider.PropertyHost
import org.gradle.api.internal.provider.ValueSupplier
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import sh.miles.cosmosis.CosmosisPlugin

class PropertyHacker(objectFactory: ObjectFactory) {

    private val propertyFactory: PropertyFactory
    private val host: PropertyHost

    init {
        val objectFactoryClazz = objectFactory::class.java
        val propertyFactoryField = objectFactoryClazz.getDeclaredField("propertyFactory")
        propertyFactoryField.trySetAccessible()
        propertyFactory = propertyFactoryField.get(objectFactory) as PropertyFactory

        val propertyHostField = propertyFactory::class.java.getDeclaredField("propertyHost")
        propertyHostField.trySetAccessible()
        host = propertyHostField.get(propertyFactory) as PropertyHost
    }

    fun hackFinalizationState(): Any {
        val defaultPropertyClazz = AbstractProperty::class.java
        defaultPropertyClazz.declaredClasses.forEach { CosmosisPlugin.LOGGER.info(it.name) }
        val nonFinalizedValueClazz =
            defaultPropertyClazz.declaredClasses.first { it.name.contains("NonFinalizedValue") }
        val constructor = nonFinalizedValueClazz.getDeclaredConstructor(PropertyHost::class.java)
        constructor.trySetAccessible()
        return constructor.newInstance(host)
    }

    fun useFinalizedState(finalizedState: Any, value: Any): ValueSupplier {
        val clazz = finalizedState::class.java
        val explicitValue = clazz.getDeclaredMethod("explicitValue", java.lang.Object::class.java)
        explicitValue.trySetAccessible()
        return explicitValue.invoke(finalizedState, value) as ValueSupplier
    }

    fun <T> setStateAndValue(property: Property<T>, finalizedState: Any, value: Any) {
        val clazz = AbstractProperty::class.java
        val stateField = clazz.getDeclaredField("state")
        stateField.trySetAccessible()
        stateField.set(property, finalizedState)
        val valueField = clazz.getDeclaredField("value")
        valueField.trySetAccessible()
        valueField.set(property, value)
    }

}
