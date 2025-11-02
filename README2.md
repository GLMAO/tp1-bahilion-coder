CS023 slama ibrahim

PROBLEM: ConcurrentModificationException
   
   Look at DummyTimeServiceImpl.java:
   
   private void secondesChanged(int oldValue, int secondes) {
       for (TimerChangeListener l : listeners) {  // ← Iterating
           l.propertyChange(SECONDE_PROP, oldValue, secondes);
       }
   }
   
   What happens with multiple countdowns?
   
   1. Timer calls secondesChanged()
   2. Loop starts: for (listener : listeners)
   3. First countdown receives propertyChange()
   4. Countdown reaches 0 and calls removeTimeChangeListener(this)
   5. BOOM! We're modifying the list WHILE iterating!
   6. ConcurrentModificationException thrown!
   
   WHY IT HAPPENS:
   - Multiple countdowns finish at different times
   - When one finishes, it removes itself from the list
   - But the loop is still iterating over that list
   - Java throws an exception to prevent corruption
   
 ----------------------------------------------------------
  PART E: EXPLANATION
   
   PropertyChangeSupport FIXES the bug because:
   
   1. It creates a COPY of the listener list before notifying
   2. Modifications to the original list don't affect the iteration
   3. Thread-safe operations
   
 