# Spring AI Chat Application

A full-stack chat application that integrates Spring Boot with AI capabilities and Angular frontend. This project demonstrates how to build a modern chat interface powered by AI models.

## 🏗️ Architecture

The project consists of two main components:

-   **Backend**: Spring Boot application with Spring AI integration
-   **Frontend**: Angular application with Material Design

## 🚀 Features

-   Real-time chat interface
-   AI-powered responses using Google's Vertex AI Gemini
-   Modern Angular frontend with Material Design
-   RESTful API architecture
-   Proxy configuration for seamless development

## 📋 Prerequisites

-   Java 24+
-   Node.js 18+
-   Angular CLI
-   Maven 3.6+
-   Google Cloud Platform account (for Vertex AI)

## 🛠️ Setup & Installation

### Backend Setup

1. Navigate to the backend directory:

    ```bash
    cd backend/spring-ai
    ```

2. Configure your environment variables by copying the example file:

    ```bash
    cp .envexample .env
    ```

3. Set up your Google Cloud credentials for Vertex AI in [`application.properties`](backend/spring-ai/src/main/resources/application.properties):

    - Update `spring.ai.vertex.ai.gemini.project-id` with your GCP project ID
    - Ensure proper authentication is configured

4. Run the Spring Boot application:
    ```bash
    ./mvnw spring-boot:run
    ```

The backend will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to the frontend directory:

    ```bash
    cd frontend/angular-ai
    ```

2. Install dependencies:

    ```bash
    npm install
    ```

3. Start the development server:
    ```bash
    npm start
    ```

The frontend will start on `http://localhost:4200` with proxy configuration pointing to the backend.

## 📁 Project Structure

```
├── backend/spring-ai/          # Spring Boot backend
│   ├── src/main/java/          # Java source code
│   │   └── dev/raniery/springai/
│   │       ├── controller/     # REST controllers
│   │       ├── dto/            # Data transfer objects
│   │       └── SpringAiApplication.java
│   └── src/main/resources/     # Configuration files
├── frontend/angular-ai/        # Angular frontend
│   ├── src/app/                # Angular components
│   │   ├── chat/               # Chat-related components
│   │   │   ├── service/        # Chat service
│   │   │   ├── response/       # Response models
│   │   │   └── simple-chat/    # Chat component
│   │   └── app.ts              # Main app component
│   └── proxy.conf.js           # Development proxy config
```

## 🌐 API Endpoints

### Chat Endpoints

-   **POST** `/api/chat/simple-chat`
    -   Send a chat message to the AI
    -   Request body: [`ChatMessage`](backend/spring-ai/src/main/java/dev/raniery/springai/dto/ChatMessage.java)
    -   Response: [`ChatMessage`](backend/spring-ai/src/main/java/dev/raniery/springai/dto/ChatMessage.java)

## 🎨 Frontend Components

### Main Components

-   [`App`](frontend/angular-ai/src/app/app.ts): Root application component with navigation
-   [`SimpleChat`](frontend/angular-ai/src/app/chat/simple-chat/simple-chat.ts): Chat interface component
-   [`ChatService`](frontend/angular-ai/src/app/chat/service/chat-service.ts): Service for API communication

### Features

-   Responsive chat interface
-   Loading indicators with typing animation
-   Error handling for failed requests
-   Material Design components

## 📝 License

This project is open source and available under the [MIT License](LICENSE).
