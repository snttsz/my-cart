# My Cart

This project was part of the assessments required by the Program Language II discipline. 

MyCart is a shopping management program designed to simplify the shopping experience across various websites, like AliExpress, Shein and Shopee. The application allows users to easily track their purchases, managing the amount already collected for each product and monitoring the remaining amount. 

It also offers various features, such as adding tags for organization, including favorite stores, registering direct links to products, and tracking values. It acts as a universal cart, streamlining the management of purchases across different websites.

Authors: <br>
[@snttsz](https://github.com/snttsz) - Glenda Santana<br>
[@LuisHBM](https://github.com/LuisHBM) - Luís Henrique Magalhães<br>
[@KalvinAlbuquerque](https://github.com/KalvinAlbuquerque) - Kalvin Albuquerque


## How to Setup 

*Java 17 or higher is required to run this program.*

### VSCode

In your Visual Studio Code, click on the "Run" button, placed in top left menu, just bellow the window name. Then, click on "Add Configuration" and choose the Java option. It will create a launch.json file in your project, so you can setup the configurations VSCode will use to compile your program. 

Search for the class that has your "main" function and create a new key named "vmArgs", put the : and open the quotation marks ("") to set the value of the key. As we're only using JAVAFX as external module, you'll just have to paste this line:

--module-path \"lib/javafx/javafx-sdk-17.0.9/lib\" --add-modules javafx.controls,javafx.fxml

Save and close the json file. Now you can run the program normally.

## Quick view on the graphic interface

* **Login Page**

![Screenshot from 2023-12-16 08-18-42](https://github.com/snttsz/my-cart/assets/109248112/1201e2c9-95df-490f-af10-a6d0a36e4955)


* **Registering Page**

![Screenshot from 2023-12-16 08-18-48](https://github.com/snttsz/my-cart/assets/109248112/c5f86ecc-f3bf-47a8-a1de-eb9c485a7902)


* **Initial Logged Page**

![Screenshot from 2023-12-16 08-18-56](https://github.com/snttsz/my-cart/assets/109248112/92b25df3-f8b8-422d-bbf8-2bd841466dec)


* **Show Product Page**

![Screenshot from 2023-12-16 08-19-04](https://github.com/snttsz/my-cart/assets/109248112/99b92bc6-ed49-4069-af8d-35f9492e3552)



