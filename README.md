# dtastr-inventory-system
Inventory system project for data structure class

## Getting Started

Dependecies:
* MySQL java connector: [click here](https://dev.mysql.com/downloads/connector/j/).

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


## Mockup Data
In the folder script you will find some SQL if you want to test the DB or anything else.
* User list and password
    * usuario1@example.com : password123
    * usuario2@example.com : abcd1234
    * usuario3@example.com : qwerty123
    * usuario4@example.com : 1234abcd

## Functionalities
* User
    * Buy product (Mockup just by name)
    * Make a devolution (Mockup just by id)
    * Ask for a product (Add a product with stock = 0)
    * get all products (See all products with'em price)
* Store
    * Add product
    * update product stock
    * update product price
    * delete product

## Bugs
This version as an alpha have certain bugs and functionalities that are not complete working, and some functionalities that are going to be when graph version is out (Planned for alpha 2 or teacher requirement)

## Things that are left
In this version the stores act as a super user, it means that the can edit everything related to items, this is somenthing that is going to be fixed on alpha 2 adding a relation between items and store, but this is just a demo where we are using the sequential data structures, so its somenthing to improbe after we implement all data structures required