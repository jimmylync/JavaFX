Assignment:
Design and create a program that can be used to generate a text file containing an event reminder,
which includes the following information: event name, date and time, location, and description.
The user should enter the information via four separate text fields (not via dialog windows or a
single text area). Your layout should contain labels to the left of each text field, indicating the
information to be entered.

Include a Button centered below the labels/fields that, when clicked, checks if all the fields contain
text and then:
• if not, creates an alert window asking the user to enter data in the missing text field
(should state the specific text field which is missing data, not a general/generic message)
• if yes, then opens a file chooser. After the user selects a file, print the data into a text file
(via a PrintWriter) in the following format, for example:

Event Name: Independence Day Fireworks
Date and Time: July 4, 9:00pm
Location: City Park
Description: a fireworks display for all to enjoy!
