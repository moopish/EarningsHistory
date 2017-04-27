## <tt>date</tt> package
<p>This package contains the <tt>Date</tt> class and 
all related functionality.</p>

### <tt>Date</tt> class
<p>Represents a date and stored the year, month and day. This 
implementation stores all created dates and limits creation of new dates 
to only unique, uncreated dates.</p>

### <tt>Day</tt> enum
<p>Stores enumerated values for each day of the week and functions 
related to days.</p>

### <tt>Month</tt> enum
<p>Stores enumerated values for each month and functions related to 
months.</p>

### <tt>Year</tt> enum
<p>Stores functions related to years.</p>

### <tt>DateFormatter</tt> interface
<p>A formatter interface for formatting given dates to a readable form. 
Has several formatter instances pre-made for use and allows for the 
creation of a custom formatter.</p>

#### <tt>CustomDateFormatter</tt> class
<p>Used in the creation of a custom formatter. Direct use is not 
allowed. Must make use of the formatter method <tt>createFormatter</tt>.
</p>