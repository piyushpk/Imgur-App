# This is thoughtctl. Imgur App 

[Mobile App Coding Challenge.pdf](https://github.com/piyushpk/Imgur-App/files/12565551/Mobile.App.Coding.Challenge.pdf)

# Problem Statement
   Create an application that searches for the top images of the week from the Imgur gallery and displays them in a list. 
   
   The app needs to provide the user with the ability to input text as part of the search query before displaying relevant results.
   
     Each cell needs to display the following for each search result:
       • title
       • date of the post in local time (DD/MM/YY hh:mm am/pm)
       • number of additional images in the post (if there are multiple)
       • image
   
   
   A search result should display the first image from its list if it contains multiple images.
    
    There needs to be a toggle feature to change the display results:
       • If the toggle is ‘List’, the app should show a list containing fullscreen width horizontal cells, with a small thumbnail along with all
         the other information arranged neatly within the cell. Up to you how you want to arrange the layout of all information listed above is displayed.
       • If the toggle is ‘Grid’, the app should display larger thumbnails in a grid-like view. The design is up to you if it looks elegant. Other
         information should still be displayed alongside each thumbnail.

  ![download](https://github.com/piyushpk/Imgur-App/assets/20608436/81de39a8-1254-4edf-afb5-b1bd1423171d)

  Search results need to be sorted in reverse chronological order.

  

# Solution
  1. Use Case Daigram:

     For above problem statement our use case diagram would look like: [Click Here](https://drive.google.com/file/d/1SfMt0uwWZFiwZc9WN_sTBBgPksNPbdvB/view?usp=sharing)

  2. UI/UX Design:

     Before start doing the actual programming we should have a good UI/UX to capture user's attention, so using **Figma** I have created a design/workflow for better understanding
     [Click Here](https://www.figma.com/proto/zOuJkJH6GbWC8DvzAbKN9J/thoughtctl---Imgur-Gallery-App-UI%2FUX-Design?type=design&node-id=23-114&t=VePUxKfz5WxhAJRH-0&scaling=min-zoom&page-id=0%3A1&starting-point-node-id=23%3A114)
     
  3. System Requirement

      To develope any software the system should have good configuration, so here I'm using OS Windows 11 with 16 GB of RAM and 512 GB of SSD to develope and run the project.
     
  4. IDE and Language

     Android App Development required software to build and run the application, so here I'm using

     Android Studio as IDE having version Android Studio Flamingo | 2022.2.1 Patch 1,

     Kotlin as a programming language with MVVM design patern using LiveData and ViewBinding having minSDk version is 23 and targetSDK version is 33.
     

  5. Libraries
     To work efficently Libraries plays vaital role in development, so I'm using below third-party Libraries
     
     1. androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1 ==> To use MVVM's live cycle efficently
     2. androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1 ==> To use MVVM's view model compose
     3. androidx.lifecycle:lifecycle-livedata-ktx:2.6.1 ==> To use LiveData
     4. com.squareup.retrofit2:retrofit:2.9.0 ==> To call API's
     5. com.squareup.retrofit2:converter-gson:2.9.0 ==> To Parse API's return response smothly
     6. com.squareup.okhttp3:logging-interceptor:4.9.0 ==> To print API logs
     7. com.intuit.sdp:sdp-android:1.0.6 ==> To provide sizes to view
     8. com.squareup.picasso:picasso:2.71828 ==> To display Images
    
  7. Imgur SetUp
     To use Imgur API's we have to follow below steps

     1. Create an account by [licking here](https://imgur.com/signin?redirect=http%3A%2F%2Fapi.imgur.com%2Foauth2%2Faddclient)
     2. Now register the app to get **Client ID** which is required to call API.
     3. In our Problem statement we have to display **TOP** images of the **WEEK** from the **Imgur gallery** in **reverse** chronological order.
     4. To use **Imgur gallery** we have https://api.imgur.com/3/gallery/{section}/{sort}/{window}/{page}?showViral=bool this API.
     5. After applying all other condition our API URL will look like https://api.imgur.com/3/gallery/top/time/week/0.json this.
     6. Setup is completed now we are ready to start the coding.
    
  8. To Be Continued...

     
    
  
