[![latest release](https://img.shields.io/github/release/GreatApo/AmazfitPaceCalendarWidget.svg?colorB=green&label=latest%20release&style=flat-square) ![release date](https://img.shields.io/badge/release%20date-2020.03.10-orange.svg?style=flat-square) ![Downloads](https://img.shields.io/github/downloads/GreatApo/AmazfitPaceCalendarWidget/total.svg?style=flat-square) ![HitCount](http://hits.dwyl.io/GreatApo/AmazfitPaceCalendarWidget.svg)](https://github.com/GreatApo/AmazfitPaceCalendarWidget/releases/latest)

# Amazfit Pace/Stratos/Verge Calendar Widget
![Amazfit Pace Calendar Widget Banner](other%20files/amazfit-calendar-widget.png)

After [Quinny899](https://github.com/KieronQuinn)'s excellent work, we are able to make widget/apps!
So, here is the first Calendar Widget for our Amazfit Pace/Stratos!



### Features
- This is a Pace/Stratos/Verge Widget (2 actually)
- Calendar & Timeline widgets
- Press gear icon for settings
- Navigate between months (Swipe / Buttons)
- Refresh to current date
- Select calendar colors
- Show/Hide year number
- Show/Hide week numbers
- Select Sunday or Monday for 1st week day
- Vibration on button touches
- Supported languages: English, Bulgarian, Chinese, Czech, Danish, Dutch, French, German, Greek, Hebrew, Hungarian, Italian, Japanese, Korean, Polish, Portuguese, Romanian, Russian, Slovak, Spanish, Thai, Turkish, Vietnamese
- Right to left Calendar support
- Settings are saved
- Calendar events are shown (new style, phone events through Amazmod or iCalendar feed/file)
- You can touch on each day for more events info
- Timeline view of the event as a widget and as a launchable app
- Support of iCalendar feed (URL) and local ICS file



### Download

Get a ready to use binary
 - From our [XDA topic](https://forum.xda-developers.com/smartwatch/amazfit/app-widget-calendar-pace-t3751889)
 - From our [Github Releases](https://github.com/GreatApo/AmazfitPaceCalendarWidget/releases/latest)

Or if you are hardcore, compile the source code with Android Studio.



### Installation
To install this widget, you will need a PC with the ADB installed. Connect your Amazfit on your PC and fire up a terminal.

```shell
adb uninstall com.dinodevs.pacecalendarwidget
adb install -r PaceCalendarWidget.X.X.X.apk
adb shell am force-stop com.huami.watch.launcher
```



### Calendar Events: How To
There are 3 ways to get your calendar evens.
1. From phone: Install Amazmod (both phone+watch) and forward phone's stock calendar events
2. From iCalendar feed: Create the file "/sdcard/Android/data/com.dinodevs.pacecalendarwidget/files/pacecalendar.txt" and write your iCalendar feed url in the first line (eg. http://mysuperdupercalendar.ics). Your events will be updated from WiFi (be connected). Powered by [iCal4j](https://github.com/ical4j/ical4j) library.
3. From iCalendar local file: Put your ics file at "/sdcard/Android/data/com.dinodevs.pacecalendarwidget/files/" name as "calendar.ics". This file is been searched only if there is no URL of method 2 and WiFi is off.

For 2 & 3, after placing the files, press the events button, the Timeline will open, press the refresh icon


### Screenshots (Version 1.9.3)
![Amazfit Pace Calendar Widget v1.9.3](other%20files/com.dinodevs.pacecalendarwidget-1.9.3.png)
![Amazfit Pace Calendar Widget v1.9.3 Settings](other%20files/com.dinodevs.pacecalendarwidget-1.9.3-settings.png)
![Amazfit Pace Calendar Widget v1.9.3 Timeline](other%20files/com.dinodevs.pacecalendarwidget-1.9.3-timeline.png)



### Thanks to the Developers

This project was made possible by:

 - GreatApo - *Widget Creator* - [ [Github](https://github.com/GreatApo) | [XDA](https://forum.xda-developers.com/member.php?u=3668555) ]
 - LFOM - *Widget Developer*
 - Quinny899 - *Widget Example Creator / Springboard Settings Creator* - [ [Github](https://github.com/KieronQuinn) | [XDA](https://forum.xda-developers.com/member.php?u=3563640) ]
 - XDA developers community (testers, translators, developers)
 - [iCal4j](https://github.com/ical4j/ical4j) library

Some more links:

 - GreatApo's [Amazfit Pace Calendar Widget - XDA topic](https://forum.xda-developers.com/smartwatch/amazfit/app-widget-calendar-pace-t3751889)
 - GreatApo's [Amazfit Timeline Widget - XDA topic](https://forum.xda-developers.com/smartwatch/amazfit/app-widget-timeline-v1-0-1-pace-stratos-t3894632)
 - Quinny's [Springboard Settings - XDA topic](https://forum.xda-developers.com/smartwatch/amazfit/app-springboard-settings-pace-rearrange-t3748651)
 - Quinny's [Widget Creation guide - XDA topic](https://forum.xda-developers.com/smartwatch/amazfit/dev-create-custom-home-screen-pages-pace-t3751731).
