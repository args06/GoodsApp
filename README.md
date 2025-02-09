<p align="center">
  <h1 align="center">GoodsApp</h1>
  <h3 align="center">Muhammad Anjar Harimurti Rahadi</h3>

</p>

# DisasterAlert App Project

This project is built for mobile disaster alerting, utilizing various Android components and libraries to handle networking, data management, and UI interactions efficiently.

## Project Structure

```
app/
├── build.gradle.kts
└── src/
    └── main/
        ├── java/com/example/app/
        |   ├── base/
        │   │   ├── GoodsApp.kt
        │   ├── di/
        │   │   ├── qualifier/
        │   │   │   └── CoroutineQualifiers.kt
        │   │   └── module/
        │   │       ├── ApplicationModule.kt
        │   │       ├── DataStoreModule.kt
        │   │       ├── NetworkModule.kt
        │   │       ├── RepositoryModule.kt
        │   │       ├── UseCaseModule.kt
        │   │       └── CoroutinesModule.kt
        │   ├── data/
        │   │   ├── local/
        │   │   │   └── UserPreferencesManager.kt
        │   │   ├── remote/
        │   │   │   ├── api/
        │   │   │   │   └── GoodsApi.kt
        │   │   │   └── model/
        │   │   │       ├── request/
        │   │   │       │   ├── LoginRequest.kt
        │   │   │       │   ├── CreateAssetRequest.kt
        │   │   │       │   └── UpdateAssetRequest.kt
        │   │   │       └── response/
        │   │   │           ├── LoginResponse.kt
        │   │   │           ├── AssetResponse.kt
        │   │   │           ├── AssetLocationResponse.kt
        │   │   │           ├── AssetStatusResponse.kt
        │   │   │           ├── ErrorResponse.kt
        │   │   │           ├── PaginatedResponse.kt
        │   │   │           └── ApiResponse.kt
        │   │   └── repository/
        │   │       ├── UserRepositoryImpl.kt
        │   │       └── GoodsRepositoryImpl.kt
        │   ├── domain/
        │   │   ├── model/
        │   │   │   ├── Asset.kt
        │   │   │   ├── AssetLocation.kt
        │   │   │   ├── AssetStatus.kt
        │   │   │   ├── Location.kt
        │   │   │   ├── PaginatedData.kt
        │   │   │   ├── Status.kt
        │   │   │   └── User.kt
        │   │   ├── repository/
        │   │   │   ├── UserRepository.kt
        │   │   │   └── GoodsRepository.kt
        │   │   └── usecase/
        │   │       ├── auth/
        │   │       │   ├── LoginUseCase.kt
        │   │       │   ├── LogoutUseCase.kt
        │   │       │   ├── IsUserLoggedInUseCase.kt
        │   │       │   └── GetCurrentUserUseCase.kt
        │   │       ├── asset/
        │   │       │   ├── GetAssetsUseCase.kt
        │   │       │   ├── CreateAssetUseCase.kt
        │   │       │   ├── GetAssetsByStatusUseCase.kt
        │   │       │   └── GetAssetsByLocationUseCase.kt
        │   │       └── base/
        │   │           └── BaseUseCase.kt
        │   ├── presentation/
        │   │   ├── reusable/
        │   │   │   ├── GoodsButton.kt
        │   │   │   ├── GoodsChart.kt
        │   │   │   ├── GoodsEditText.kt
        │   │   │   └── GoodsStatusCard.kt
        │   │   ├── state/
        │   │   │   ├── AuthUiState.kt
        │   │   │   ├── MainUiState.kt
        │   │   │   └── AssetsUiState.kt
        │   │   └── ui/
        │   │       ├── auth/
        │   │       │   ├── LoginFragment.kt
        │   │       │   └── AuthViewModel.kt
        │   │       └── main/
        │   │       │   ├── assets/
        │   │       │   │   └── AssetListFragment.kt
        │   │       │   ├── home/
        │   │       │   │    ├── HomeViewModel.kt
        │   │       │   │    └── HomeFragment.kt
        │   │       │   ├── MainActivity.kt
        │   │       │   └── MainViewModel.kt
        │   │       └── splashscreen/
        │   │           ├── SplashActivity.kt
        │   │           └── SplashViewModek.kt
        └── res/
            └── layout/
                ├── activity_login.xml
                ├── activity_main.xml
                ├── activity_splash.xml
                ├── fragment_asset.xml
                ├── fragment_home.xml
                ├── section_header.xml
                ├── view_chart.xml
                ├── view_goods_button.xml
                ├── view_goods_edittext.xml
                └── view_status_card.xml
```

## Dependencies Used
The following dependencies are used in the project,
| Name | Version |
|--|--|
| [Kotlin](https://kotlinlang.org/) | 1.9.24 |
| [Glide](https://bumptech.github.io/glide/) | 4.15.1 |
| [Hilt](https://dagger.dev/hilt/) | 2.50 |
| [Preferences Datastore](https://developer.android.com/jetpack/androidx/releases/datastore) | 1.0.0 |
| [Retrofit 2](https://square.github.io/retrofit/) | 2.11.0 |
| [Gson Converter](https://square.github.io/retrofit/) | 2.11.0 |
| [OkHttp 3](https://square.github.io/okhttp/) | 4.12.0 |
| [Kotlinx Coroutines Test](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/) | 1.7.3 |
| [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) | v3.1.0 |
| [Chucker](https://github.com/ChuckerTeam/chucker) | 4.1.0 |

## Getting Started
1. Fetch the latest source code from the master branch.

``` 
git clone https://github.com/args06/GoodsApp.git
```

3. Run the app with Android Studio.


## Author
This project is developed by [Anjar Harimurti](https://github.com/args06).

## Contact
You can reach out to me directly at [E-mail](mailto:<anjarharimurti.ah@gmail.com>).

<div align="center">

### Show some support by starring 🌟 the repository! Thanks fo visitting.

</div>
