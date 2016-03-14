# Project 1 - *Bee Instagram*

**Bee Instagram** is an android app that allows a user to check out popular photos from Instagram. The app utilizes Instagram API to display images and basic image information to the user.

Time spent: **30** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **scroll through current popular photos** from Instagram
* [X] For each photo displayed, user can see the following details:
  * [X] Graphic, Caption, Username
  * [X] Relative timestamp, likes count, user's profile image

The following **optional** features are implemented:

* [ ] User can **pull-to-refresh** popular stream to get the latest popular photos
* [X] User can see latest comments for each photo (**bonus** - Show last 2 comments for each photo)
* [X] User can see all the profile images in circles ()
* [X] Display a nice default placeholder graphic for each image during loading
* [X] Improved the user interface through styling and coloring

The following **bonus** features are implemented:

* [X] Allow user to view all comments for an image within a separate activity or dialog fragment
* [ ] Allow video posts to be played in full-screen using the VideoView
* [ ] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce boilerplate code.

The following **additional** features are implemented:

* [X] Use RecyclerView with 2 Customize ViewHolder (depend on item type,recyclerView will change style of item inside) 
* [X] Extend AsyncHttpClient so I can process callback function (Using a part of Observer design pattern)
* [X] Using TabView and ViewPager for loading Image and Video (I found it a crazy thing, I can use Horizontal RecyclerView instead)
* [X] Using Toolbar "scrolling Enter Always animation" (CoordinatorLayout,nestedlayout,appbarlayout with toolbar inside)
* [X] I created a globalVariable Class which can resize the layout for almost device (I depend on the percent of layout height(weight))

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

- [Imgur](http://i.imgur.com/tKMNVFK.gifv)

- [Youtube](https://www.youtube.com/watch?v=stT61gS2s7s&feature=youtu.be)


## Notes
- It's difficult to use RecyclerView with multiple style and use it inside ScrollView Hierachy :(
- The performance isn't hight because I don't using the HorizontalRecyclerView at the bottom (I used my customized tablayout)
- The design is very bad,I don't know how to arrange layouts together clearly

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

    Copyright [2016] [Nguyen Hoang Phat]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
