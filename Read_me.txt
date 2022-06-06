If anything is unclear, I have created two tutorial videos. A tutorial for the database and one for the app :) 


Database:
https://dashboard.back4app.com/apps

Login Via Github ("I will change the password in a view days :) ")
samuel.spencer@edu.fh-joanneum.at
The_Big_Project




Go to Projekt Joanneum
Then Database: All_Works which represent are all Projects
Database:      New User which represent are all Users

If the app crashes, please check if the database says "undefined" anywhere. This usually leads to an error. sorry for that. It is a rare known error!


Project

Unzip project folder.
Theme size setting is Tablet. 10.5 Zoll 1600x2560 Android 11

Start The App.

You will then see the student registration.
You can create an account or switch to login.

Login
There are three login Interfaces. Example login.  
Otherwise you can fetch the users from the database

Student: 	austrian_spencer2@outlook.com 		Pas 12345
Assistant: 	austrian_spencer@outlook.com 		Pas 12345
Admin:	 	Administrator@outlook.com		Pas 12345

Assistant is the professor.
Password is always 12345.
Have Fun ^^






-------------------------------------------------------------------------------------



Student Sign Up
Here students can register. Administrators and assistants are created in the database or by the administrator.

Interface login
Here you can switch between the login for students, assistants and administrators.

Login (Student / Assistant / Admin)
Users can register here. If you are in the wrong login, you will get a message from the system. The first login always takes a little longer.

Student interface
Here you can book projects, bachelor and master, as well as look at all completed projects. Not booked ones!

Projects
You can book a project here. The two buttons show all available projects and teachers (including the number of students they will still take in). To book a ptoject, you write the project Name as well as the professors Name
in to the designated fields.

Bachelor:
Same as project. The difference is that you have to have a completed project and not yet have a bachelor's degree.


Master:
Same as Bachelor only the rules are adjusted.

Completing a project.
To complete a project / bachelor or master, you simply have to go back to the student login and log in again. I thought that would be the best way to present it.

Assistant Login:
Login for assistants. (Assistants are the professors)

Assistant Slots:
Here the professor can see the assigned students and the number of still open slots.

Admin Login:
Login for administrators.

Danger!
Administrators have absolute write permission. You can read write as well as change any data. This can lead to redundant data in certain fields.
Example if you make a student a professor (assistant) who already booked some projects. However, these fields are marked.


Admin interface:
There are two interfaces where you can access the different functions.

Add User:
Here you can add a user.

Add work:
Here you can create different projects. Unfortunately, the date (Datum) has to be pressed twice.

Logout
Interface login

Delete User or Work
Here you can delete all users and projects. If you delete a user, the projects will be unlocked again. When you delete a project. The project is also deleted from the assigned student. Open projects are marked.

Change User
Here you can change all user data. Redundant data can result from marked fields.


Change work
The data of all projects can be changed here. For projects that have been booked, redundant data will be caused. Caution!


There is also a class where I create an object that I access in several classes to reduce the amount of database calls.
















Mehr Kommentare setzen
Mehr Object orientiert 
