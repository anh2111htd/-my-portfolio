// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.*;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> eventCollection, MeetingRequest request) {
    // Sort by start date 
    List<Event> events = new ArrayList(eventCollection);
    events.sort((Event e1, Event e2) -> e1.getWhen().start() - e2.getWhen().start());

    Collection<TimeRange> result = new ArrayList<TimeRange>();
    int prevStart = TimeRange.START_OF_DAY, currStart, duration;
    for (int i = 0; i < events.size(); i++) {
      if (!Collections.disjoint(request.getAttendees(), events.get(i).getAttendees())) {
        currStart = events.get(i).getWhen().start();
        if (prevStart < currStart) {
          duration = currStart - prevStart;
          if (duration >= request.getDuration()) {
            result.add(new TimeRange(prevStart, duration));
          }
        }
        prevStart = Math.max(prevStart, events.get(i).getWhen().end());
      }
    }

    // Add the timeslot lasting until the end of the day
    duration = TimeRange.END_OF_DAY + 1 - prevStart;
    if (duration >= request.getDuration()) {
      result.add(new TimeRange(prevStart, duration));
    }
    return result;
  }
}
