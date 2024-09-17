# Random User Assessment Project

This project is based in some ideas of Clean architecture I've been developing lately on other projects like [Pokompose](https://github.com/marcRDZ/Pokompose) or [MarvelArchives](https://github.com/marcRDZ/MarvelArchives) but dealing with the endpoint provided on this assessment. By the way I've simplified or improved some things on the base classes and take advantage of the latest features of Android Studio like version catalogs and so.

This diagram expose my proposal of architecture:

![Random User project architecture](http://drive.google.com/uc?export=view&id=175NcPUgKV-NA53HgOLQETY3v7IFLkA26)

I use to work with a kind of modularized architecture separated on three layers for UI, domain and data, with a domain isolated of the framework as a pure kotlin project.

My idea with this project was to split the domain on three modules following the dependency inversion principle to decouple the side dealing with the framework for data from the other that use the UI. Beside this I try to implement a pure kotlin Presentation layer that contains the UI logic in a agnostic and testable way, so as the Data layer implements **repositories** and the Domain contains the **use cases**, I've created an `EventFlowHandler` interface as a bridge between `ViewModels` and the **use cases**, it's main purpose is to contain the flow of reactions from UI events to the resulting states.

I gather here all the things I've been learning through my latest working experience:

- Asynchronous handling with [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- UI reactive state management with [Flows](https://kotlinlang.org/docs/flow.html)
- Functional programming support through [Λrrow](https://arrow-kt.io/)
- Implementation of unit tests for the core logic based on [Mockk](https://mockk.io/) and [Turbine](https://github.com/cashapp/turbine)
- UI fully designed on [Jetpack Compose](https://developer.android.com/jetpack/compose)

> Written with [StackEdit](https://stackedit.io/).
