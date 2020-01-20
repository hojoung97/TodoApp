# TodoApp
This is my personal Android mini project that I have built as my final project for Software Saturday program at Purdue University.

## Overview

## Components of the App along with References Used
- [RecyclerView](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView) with [CardView](https://developer.android.com/reference/androidx/cardview/widget/CardView): [developer's guide](https://developer.android.com/guide/topics/ui/layout/cardview.html)
- Swipe Actions on RecyclerView Items: [blog reference](https://codeburst.io/android-swipe-menu-with-recyclerview-8f28a235ff28)
- Internal [Itents](https://developer.android.com/reference/android/content/Intent) with [Buttons](https://developer.android.com/reference/android/widget/Button): [sending data back to MainActivity](https://developer.android.com/reference/android/app/Activity.html#startActivityForResult(android.content.Intent,%20int))
- [Notifications](https://developer.android.com/reference/android/app/Notification): [creating a Notification](https://developer.android.com/training/notify-user/build-notification)
- [NotificationChannel](https://developer.android.com/reference/android/app/NotificationChannel): [create a NotificationChannel](https://developer.android.com/training/notify-user/channels)
- [AlarmManager](https://developer.android.com/reference/android/app/AlarmManager) and [BroadcastReceiver](https://developer.android.com/reference/android/content/BroadcastReceiver) to schedule notification timings: [Github Gist example](https://gist.github.com/BrandonSmith/6679223)
