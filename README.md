# BeeInstagram
Assignment:Week 1 Project: Instagram Photo Viewer

Just fetch data from Instagram API to mobile

Submitted by: Hoang Phat Nguyen - lazybee27102@gmail.com

Time spent: 30 hours in total 

The following functionality is completed:

* [X] User can see the most popular Image and Video of Instagram
* [X] User can like (love) that image or video (just animation,nothing inside)
* [X] User can scroll through current popular photos from Instagram (with Horizontal tab and fragment)
* [X] For each photo displayed, user can see the following details:Graphic, Caption, Username,relative timestamp, like count, user profile image,location,comment count,all the latest comments,

The following optional features are implemented:

* [ ] Add pull-to-refresh for popular stream with SwipeRefreshLayout (I didn't use the scrollview,I used tabview)
* [X] Show latest comment for each photo 
* [X] Display a nice default placeholder graphic for each image during loading (read more about Picasso)
* [X] Improve the user interface through styling and coloring
* [X] Allow user to view all comments for an image within a separate activity or dialog fragment 
* [ ] Allow video posts to be played in full-screen using the
* [ ]Apply the popular Butterknife annotation library to reduce view boilerplate.

The following **additional** features are implemented:

- Use RecyclerView with 2 Customize ViewHolder (depend on item type,recyclerView will change style of item inside) 
- Extend AsyncHttpClient so I can process callback functioc (Using a part of Observer design pattern)
- Using TabView and ViewPager for loading Image and Video (I found it a crazy thing, I can use Horizontal RecyclerView instead)
- Using Toolbar "scrolling Enter Always animation"
- I created a globalVariable Class which can resize the layout for almost device (I depend on the percent of layout height(weight))

Here's a walkthrough of implemented user stories:
* [X] OrignalLink: http://i.imgur.com/QxZzgbn.gifv

<img src='http://imgur.com/tKMNVFK' title='BeeInstagram Walkthrough' width='' alt='BeeInstagram Walkthrough' />


# Notes
- It's difficult to use RecyclerView with multiple style and use it inside ScrollView Hierachy :(
- The performance isn't hight because I don't using the HorizontalRecyclerView at the bottom (I used my customized tablayout)
- The design is very bad,I don't know how to arrange layouts together clearly

