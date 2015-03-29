# Chain

Chain is a java application that can be used to generate a huge list, a so called "Chain", of events from various data sources.
There are a lot of ways in which Chain could be used. For example if you want to document a project you could combine
calendar events, meeting reports, photos and more to output one timeline/chain of events.  
Another example would be to document ones life events. Google & Facebook already have precises data about our lives
so why shouldn't we be able to aggregate that data for personal use as well. With such a chain of data you'd be able
to tell what happened each day of your life even if it was a few years ago and you don't remember.

##How does it work?
Chain consists of "Producers" that gather data and transform it into a list of "ChainEvent"s. That list can then be
output via a "Publisher" of your choice.

##Current features
 + **Producers** (gather data)
   + **DirectoryFileProducer:** All files in a folder, organized by their creation date. The creation-date can either be specified in the filename or automatically read from the meta-data.
   + **GoogleCalendarProducer:** Download all the events in your primary Google Calendar.
   + **SimpleCalendarProducer:** Aggregate periodic events, such as birthdays etc. You can specify or omit a end-date.
 + **Publishers** (output data)
   + **HTMLPublisher:** Generate a HTML file containing a paginated list of all events. The list of events is generated via infinity.js so it can be as long as you want without performance impact.
   + **TextPublisher:** Outputs the chain of events to the console.

##Planned features
 + Directly embed images/photographs aggregated via DirectoryFileProducer.
 + Directly embed text aggregated via DirectoryFileProducer.
 + Nicer design of website output by HTMLPublisher.
 + Configuration, so that Chain can be used as binary without an IDE.
 + Library version, so that Chain can bed embedded in other projects.

##License
The MIT License (MIT)

Copyright (c) 2015 Theo Winter

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.