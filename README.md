# Project-manager

![Java](https://img.shields.io/badge/Java-11+-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5-green.svg)
![React](https://img.shields.io/badge/React-18.2.0-blue.svg)
![MySQL](https://img.shields.io/badge/Database-MySQL-yellow.svg)
![License](https://img.shields.io/badge/License-MIT-blue.svg)
![Contributions](https://img.shields.io/badge/Contributions-Welcome-brightgreen.svg)

Project-Manager is a project management web application designed specifically for software development teams. The platform facilitates seamless collaboration between team members by allowing users to create projects, assign tasks, update task statuses, and communicate in real-time. Built with a robust Java Spring Boot back-end, a responsive React front-end, and MySQL database, Wera-Manager empowers software development teams to streamline project workflows and maximize productivity.

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **User Registration and Authentication**: Users register using their email, username, and password. After registration, they are automatically logged into the platform.
- **Project Management**: Users can create, view, and delete projects. A search feature helps quickly locate specific projects.
- **Collaboration Invitations**: Project creators can send email invitations to other developers to join projects.
- **Task Management**: Create, assign, and track tasks, updating task statuses as progress is made.
- **Communication Tools**: Each project has a chatbox for real-time communication and a comment section on individual tasks to facilitate discussions and feedback.
  
---

## Tech Stack

- **Front-end**: React
- **Back-end**: Java Spring Boot
- **Database**: MySQL
- **Other**: Email integration for invitation management

---

## Installation

### Prerequisites
- Java 11 or later
- Node.js and npm
- MySQL server

### Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/Wera-Manager.git
   cd Project-Manager
2. **Back-end Setup**

Navigate to the backend directory.
Configure your MySQL database settings in application.properties.
Build and run the Spring Boot application:
  ```bash
   mvn clean install
   mvn spring-boot:run
   ```
3. **Front-end Setup**

Navigate to the frontend directory.
Install dependencies and start the React development server:
```bash 
  npm install
  npm start
  ```
Access the Application

Open a browser and go to http://localhost:3000.

Usage
Registering and Logging In
1. Register with an email, username, and password.
2. Upon registration, you will be automatically logged into the platform.

Creating Projects

1. Click on Create Project to start a new project.
2. Use the Search Bar to quickly locate a specific project among the ones you've created.

Inviting Collaborators

1. Open a specific project, then use the Email Invitation feature to invite other developers to collaborate.
2. Invited developers can join the project upon accepting the invitation.

Managing Tasks
1. Inside the project page, create tasks and assign them to team members.
2. Update the task status as work progresses, and leave comments on tasks as needed.
Real-Time Communication
Use the Chatbox within each project for real-time communication with team members.
Collaborators can also leave Comments on specific tasks for detailed discussions.

**Contributing**
We welcome contributions! To get started:

**Fork the repository.**
Create a new branch for your feature or bug fix.
Submit a pull request for review.
For major changes, please open an issue to discuss your ideas first.

**License**
This project is licensed under the MIT License - see the [LICENSE](https://choosealicense.com/licenses/mit/) file for details.
