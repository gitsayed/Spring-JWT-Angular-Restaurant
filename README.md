# Spring-JWT-Angular-Restaurant
Assalamu  Alaikum.

This is an Online Restaurant Project.

Technologies are following we use here:

1: Java jdk-8, SpringBoot-v2.7.7 <br>
2: Angular v16 with PrimeNg v15 <br>
3: PostgreSQl Database v10.<br>
4: Maven build tool.<br>
5: Tomcat Server [embedded with springboot]. <br>
6: Node JS 18.

Description of steps to deploy the project.

1: First Clone the Project.<br>
2: Create a database in Postgresql by name "testdb".<br>
3: Open the backend project in your ide.<br>
4: Build the backend project.<br>
5: Open the Frontend Project in any ide. <br>
6: Install nodejs 18.<br>
7: Install npm Libraries in frontend project location terminal.<br>
8: Now write "ng serve" in frontend project location terminal.<br>
9: Backend server port: "8080" and frontend server:"4200"<br>
10: When the backend project will be started it will automatically create table for itself.<br>
11: Now run the following Role SQL Script in your database.<br>
12: Create a user from frontend.<br>
13: Make 'ACTIVE' the user from database table 'users'.<br>
14: Assign the user some roles in 'user_roles' table.<br>
15: Now use the whole application.<br>





#ROLE SQL SCRIPT

INSERT INTO public.roles(id, name) VALUES (1, 'ROLE_USER');

INSERT INTO public.roles(id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO public.roles(id, name) VALUES (3, 'ROLE_SUPER_ADMIN');

INSERT INTO public.roles(id, name) VALUES (4, 'ROLE_MODERATOR');

INSERT INTO public.roles(id, name) VALUES (5, 'ROLE_FOOD');

INSERT INTO public.roles(id, name) VALUES (6, 'ROLE_CATEGORY');

INSERT INTO public.roles(id, name) VALUES (7, 'ROLE_DISH');

INSERT INTO public.roles(id, name) VALUES (8, 'ROLE_ORDER');

INSERT INTO public.roles(id, name) VALUES (9, 'ROLE_RESTAURANT');

INSERT INTO public.roles(id, name) VALUES (10,'ROLE_CUSTOMER');



The Project is still in development.

#Thank You.