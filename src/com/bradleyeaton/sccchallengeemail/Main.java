package com.bradleyeaton.sccchallengeemail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolFamily;
import java.net.URL;
import java.net.URLConnection;

public class Main {

    public static void main(String[] args) {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in)); //Setup user input

        System.out.println("Please enter an email ID:");

        try {
            String emailID = inputReader.readLine(); //Read the users input

            String fullURLString = "https://www.ecs.soton.ac.uk/people/" + emailID; //Create the URL as a string through concatenation
            URL fullURL = new URL(fullURLString); //Convert the string into a URL object
            URLConnection urlConnection = fullURL.openConnection(); //Open a connection to the web address

            BufferedReader httpReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            try {
                String httpLine = null;
                for(int i = 0; i < 8; i++) { //Get the buffered reader onto the 9th line
                    httpLine = httpReader.readLine();
                }
                //Then read the eighth line

//                httpLine = httpLine.substring(11, httpLine.length() - 8); //Remove 12 characters from the front and 8 from the end to remove the HTML from the string


                httpLine = httpLine.trim().replaceAll("</?title>",""); //removes the title tags and whitespace

                String[] titleParts = httpLine.split("\\|"); //Split on the |

                String name = titleParts[0].trim(); //remove whitespace from name
                System.out.println(name); //Prints out the name


            }
            finally {
                httpReader.close(); //Ensure that the Stream is closed
            }

        }
        catch (Exception ex){
            System.out.println("An error occurred, possible issue with emailID");
        }
    }
}
