# Cosmosis
Welcome to cosmosis the only logical way to mod with Cosmic Reach. Cosmosis provides
ease of use tools for modding Cosmic Reach with minimal effort.

## Features
- Automatic Loader and Environment Setup
- Configurable Environment Settings
- Run from in IDE using the `gradle runClient` task

## Example
Example Project at [CosmosisExample](https://github.com/Y2Kwastaken/CosmosisExample)

## Getting Started

To get started with using cosmosis in your project add cosmosis to your plugins

settings.gradle.kts
```kotlin
pluginManagement {
    repositories {
        maven("https://maven.miles.sh/snapshots")
    }
}
```

build.gradle
```kotlin
plugins {
  id("sh.miles.cosmosis") version "1.0.0-SNAPSHOT"
}
```

Now before going any further you need to setup the environment run `gradle setup`
to setup the cosmosis environment. This is necessary before progressing any further

Once the cosmosis environment is setup you can add its bundles. Ideally this should
be done near the top of your build.gradle for clarity but it could be put anywhere
```kotlin
cosmosis.repoBundle()
cosmosis.devBundle()
```

Horray!! Cosmosis is now setup!

## FAQ

### Trouble Finding Fabric Loader

Q: Could not find sh.miles.cosmicloader:CosmicLoader:1.0.0-SNAPSHOT

A: If you see this error or similar make sure that your fabric loader of choice is
installed into your maven m2 folder. If your loader does not provide a repository
you can install the file manually! See the accepted answer on [this](https://stackoverflow.com/questions/4955635/how-to-add-local-jar-files-to-a-maven-project](https://stackoverflow.com/a/4955695)
stackoverflow thread.

### Trouble Finding Cosmic Reach

Q: Could not find finalforeach:cosmicreach:0.1.7

A: If you see this error that means CosmicTools has not been run yet. CosmicTools should
run automatically when using the `gradle setup` command however, if this did not occur you
can run CosmicTools manually by downloading it from the github [CosmicTools Github](https://github.com/Y2Kwastaken/CosmicTools)

**Think you have a FAQ? Feel free to make an issue or pullrequest and request the addition**

## Configuration

Cosmosis is fairly configurable so it will be nearly impossible to go through everything
within this README section in depth however, I will cover the likely to be most used configuration
settings.

### Dev Bundle

the cosmosis dev bundle can be configured to obtain the loader from a different dependency
other than my [CosmicLoader](https://github.com/Y2Kwastaken/CosmicLoader). You can do this by specifying
optional parameters in the devBundle. For this example I will be using ForwarD-Nern's [CosmicReach-Mod-Loader](https://github.com/ForwarD-NerN/CosmicReach-Mod-Loader)

```kotlin
cosmosis.devBundle(loaderGroup = "ru.nerm", loaderArtifact = "CRModLoader", loaderVersion = "1.1.1")
```

**Note** by changing your modloader it does not absolve you of installing it into your .m2 you can see more info at [Trouble Finding Fabric Loader](#trouble-finding-fabric-loader)

### Loader Download Task

The loader download task automatically downloads [CosmicLoader](https://github.com/Y2Kwastaken/CosmicLoader) by default, however,
you can change this by inserting your own url. an example using ForwarD-Nern's [CosmicReach-Mod-Loader](https://github.com/ForwarD-NerN/CosmicReach-Mod-Loader)
is below

```kotlin
tasks.downloadLoader {
    this.loaderLink = uri("https://github.com/ForwarD-NerN/CosmicReach-Mod-Loader/releases/download/latest/cosmicreach-fabric-modloader.zip").toURL()
}
```

### Run Client Task

By default the task `gradle runClient` searches for a `launcher.sh` or `launcher.bat`, however,
you can change what the base name of this launcher is for example if the launcher is named
`launch.sh` instead of `launcher.sh` you can configure it to do so. An example is below

```kotlin
tasks.runClient {
    launcherFileGeneric = "launch"
}
```
