package com.library.step_definitions;

import com.library.pages.BooksPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.DBUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BookTableStepDefs {
    BooksPage booksPage = new BooksPage();

    @Then("book information must match the database for {string}")
    public void book_information_must_match_the_database_for(String bookName) {
        BrowserUtils.waitFor(3);
       //get information from UI
        String actualName = booksPage.bookName.getAttribute("value");
        String actualAuthor = booksPage.author.getAttribute("value");
        String actualISBN = booksPage.isbn.getAttribute("value");
        String actualYear = booksPage.year.getAttribute("value");
        String actualDesc = booksPage.description.getAttribute("value");

        System.out.println("actualName = " + actualName);
        System.out.println("actualAuthor = " + actualAuthor);
        //get information from database

        String query = "select name, isbn,year,author,description from books\n" +
                "where name = '"+bookName+"'";

        //since we are getting only one row, we will use getRowMap method
        Map<String, Object> dbData = DBUtils.getRowMap(query);

        System.out.println(dbData.toString());

        String expectedName = dbData.get("name").toString();
        String expectedISBN = dbData.get("isbn").toString();
        String expectedYear = dbData.get("year").toString();
        String expectedAuthor = dbData.get("author").toString();
        String expectedDesc = dbData.get("description").toString();

        //compare UI vs Database
        Assert.assertEquals("did not match name",expectedName,actualName);
        Assert.assertEquals("did not match Author",expectedAuthor,actualAuthor);
        Assert.assertEquals("did not match Year",expectedYear,actualYear);
        Assert.assertEquals("did not match ISBN",expectedISBN,actualISBN);
        Assert.assertEquals("did not match Description",expectedDesc,actualDesc);

        //real life you dont save those info into variables
        Assert.assertEquals(booksPage.bookName.getAttribute("value"),dbData.get("name").toString());



    }


}
