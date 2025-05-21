# Extracurricular Registration App

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)

A mobile application for SMA Muhammadiyah students to register for extracurricular activities, built with Android and Kotlin.

## Features

- **Student Information Collection**:
  - Name input
  - NIS (Student ID) input
  - Class selection via Spinner
  - Date of birth picker
  - Gender selection

- **Activity Registration**:
  - Multiple extracurricular selection (Checkboxes)
  - Schedule selection with autocomplete suggestions
  - Form validation

- **Data Submission**:
  - All collected data shown in confirmation screen
  - Proper data passing between activities

## Screenshots

| Form Screen | Confirmation |
|-------------|--------------|
| ![Form](screenshots/form.jpg) | ![Confirmation](screenshots/confirmation.jpg) |

## Technology Stack

- **Language**: Kotlin
- **Minimum SDK**: API 21 (Android 5.0)
- **Architecture**: Single Activity with Multiple Fragments
- **Components**:
  - ScrollView for form layout
  - AutoCompleteTextView for schedule suggestions
  - DatePickerDialog
  - RadioGroups and Checkboxes
  - Intent-based navigation

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/dorumon234/Event-Handling-AndroidStudio.git
