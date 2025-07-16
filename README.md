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

![news-ai-5](https://github.com/user-attachments/assets/a7aa5e54-738a-4877-8647-db8bf9ffcea3)

![newsAI-2](https://github.com/user-attachments/assets/1a9ff291-81d6-475d-8bff-eddf7039186b)


![News-ai-3](https://github.com/user-attachments/assets/6934797b-27ba-4804-b098-20a34bb0eb30)

![new-ai-4](https://github.com/user-attachments/assets/c00d64f7-2bd7-4d99-a01c-9410ea58fd9e)

![news-ai-5](https://github.com/user-attachments/assets/e3827c36-169e-4b17-9565-fa5ec5136240)


## 📄 License
This project is open source and free to use under the [MIT License](LICENSE).
