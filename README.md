# TodoApp
This is my personal Android mini project that I have built as my final project for Software Saturday program at Purdue University.

I made a simple app that can track things to do. Users can add and delete number of things that need to be finished and managed.

## Quick Peek
<img src="https://github.com/hojoung97/TodoApp/blob/master/readme_images/androidDemo.gif" width="420">

## Components of the App along with References Used
- [RecyclerView](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView) with [CardView](https://developer.android.com/reference/androidx/cardview/widget/CardView): [developer's guide](https://developer.android.com/guide/topics/ui/layout/cardview.html)
- Swipe Actions on RecyclerView Items: [blog reference](https://codeburst.io/android-swipe-menu-with-recyclerview-8f28a235ff28)
- Internal [Itents](https://developer.android.com/reference/android/content/Intent) with [Buttons](https://developer.android.com/reference/android/widget/Button): [sending data back to MainActivity](https://developer.android.com/reference/android/app/Activity.html#startActivityForResult(android.content.Intent,%20int))
- [Notifications](https://developer.android.com/reference/android/app/Notification): [creating a Notification](https://developer.android.com/training/notify-user/build-notification)
- [NotificationChannel](https://developer.android.com/reference/android/app/NotificationChannel): [create a NotificationChannel](https://developer.android.com/training/notify-user/channels)
- [AlarmManager](https://developer.android.com/reference/android/app/AlarmManager) and [BroadcastReceiver](https://developer.android.com/reference/android/content/BroadcastReceiver) to schedule notification timings: [Github Gist example](https://gist.github.com/BrandonSmith/6679223)

## High-level Overview
Internal Intents – internal intents are used to switch between MainActivity and TodoAddActivity. Users can navigate between the two activities using buttons' onClick methods that also exchange data entered by users.

RecyclerView – recycler view holds and displays list of TODOs using the card view as an individual list item.

Notification – notifications are made and sent to users to remind/alarm the users with TODOs with specified due date/time

AlarmManager - AlarmManager schedules the task to alarm or signal BroadCastReceiver at the user-specified date and time.

BroadcastReceiver – BroadcastReceiver waits for signal from the app’s AlarmManager. Once it receives signal it creates and issues a notification related to a TODO item.
