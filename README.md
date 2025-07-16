# News App Kotlin

A modern News Application built with **Kotlin** for Android. The app fetches top headlines, supports news search, and offers article summarization using **Gemini API** (free API from Google AI Studio).

## 📱 Features
- View top headlines
- Search news by keyword
- Read full articles in an in-app WebView
- Summarize articles with:
  - Brief summary
  - Detailed summary
  - Bullet points
- Pull to refresh
- Error handling and loading indicators

## 🚀 Tech Stack
- **Kotlin**
- **MVVM Architecture**
- **Retrofit** for networking
- **LiveData** & **ViewModel**
- **OkHttp** for API requests
- **Gemini API** for text summarization
- **RecyclerView** for listing articles
- **In-App WebView**

## 🔐 API Keys
This project uses external APIs:
- **News API**
- **Gemini API**

> ⚠️ **Important:**  
API keys are stored in the `Constant` folder which is **excluded from Git** using `.gitignore`.  
To run the app, create your own API keys:
- News API: [https://newsapi.org](https://newsapi.org)
- Gemini API: [https://aistudio.google.com/app/apikey](https://aistudio.google.com/app/apikey)

And define them in:
app/src/main/java/com/example/news_app_kotlin/Constant/ApiKey.kt

## 🛠️ Setup Instructions
1. Clone the repository
2. Add your API keys in `ApiKey.kt`
3. Build and run the app on Android Studio

## 📸 Screenshots
![WhatsApp Image 2025-07-16 at 13 31 55_cfaf2978](https://github.com/user-attachments/assets/356971aa-3056-4628-8527-9736f3f27cb0)


![WhatsApp Image 2025-07-16 at 13 32 32_343db9fc](https://github.com/user-attachments/assets/6888925e-bf9a-4ce6-9e53-9d85e111e0ce)


![WhatsApp Image 2025-07-16 at 13 33 05_ad448dfc](https://github.com/user-attachments/assets/a8b93ec2-a038-4e5c-a067-99a4a19a1464)


![WhatsApp Image 2025-07-16 at 13 35 21_356d66e0](https://github.com/user-attachments/assets/fbc09916-0f28-4cd0-a065-b952cf477983)


## 📄 License
This project is open source and free to use under the [MIT License](LICENSE).
