//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Dancing Badgers
// Course: CS 300 Spring 2023
//
// Author: Sebastian Lau
// Email: sglau@wisc.edu
// Lecturer: (Mouna Kacem or Hobbes LeGault)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE (identify each by name and describe how they helped)
// Online Sources: NONe (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Random;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;


public class DancingBadgers {
  private static PImage backgroundImage; // PImage object that represents the
  // background image
  private static Badger[] badgers; // perfect size array storing the badgers
  // present in this application
  private static Random randGen; // Generator of random numbers

  public static void main(String[] args) {

    Utility.runApplication(); // starts the application
  }

  public static void setup() {
    randGen = new Random();
    badgers = new Badger[5];
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
  }

  /**
   * Draws and updates the application display window. This callback method called in an infinite
   * loop.
   */

  public static void draw() {
    Utility.background(Utility.color(255, 218, 185));
    // load the image of the background


    // Draw the background image to the center of the screen
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);
    for (int i = 0; i < badgers.length; i++) {
      if (badgers[i] != null) { // constantly draws badgers if they're not null
        badgers[i].draw();
      }
    }
  }

  /**
   * Checks if the mouse is over a specific Badger whose reference is provided as input parameter
   *
   * @param Badger reference to a specific Badger object
   * @return true if the mouse is over the specific Badger object passed as input (i.e. over the
   *         image of the Badger), false otherwise
   */
  public static boolean (Badger Badger) {
    PImage badgerImage = Badger.image(); // creates variable with PImage of badger
    if (Utility.mouseX() <= (Badger.getX() + badgerImage.width / 2)) {
      if (Utility.mouseX() >= (Badger.getX() - badgerImage.width / 2)) {
        if (Utility.mouseY() <= (Badger.getY() + badgerImage.height / 2)) {
          if (Utility.mouseY() >= (Badger.getY() - badgerImage.height / 2)) {
            return true; // the if statements check if mouse is on the badger
          }
        }
      }
    }

    return false;
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    boolean allowMove = true; // I made a variable to check if a badger is already being moved
    for (int i = 0; i < badgers.length; i++) {
      if (badgers[i] != null) {
        if (badgers[i].isDragging()) {
          allowMove = false;
        }
      }
    }
    if (allowMove == true) {// if allowMove is false than a badger is already being moved
      for (int y = 0; y < badgers.length; y++) {
        if (badgers[y] != null) {
          if (isMouseOver(badgers[y])) { // checks if mouse is over a badger
            badgers[y].startDragging();
            break; // breaks after taking one badger at the lowest index so you don't drag
          } // multiple
        }
      }
    }


  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    for (int i = 0; i < badgers.length; i++) {
      if (badgers[i] != null) {
        badgers[i].stopDragging();// stops dragging of all non null badgers
      }
    }
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {
    if (Utility.key() == 'b' || Utility.key() == 'B') {
      for (int i = 0; i < badgers.length; i++) {
        if (badgers[i] != null) { // checks for empty badger spots
          continue;
        }
        badgers[i] = new Badger((float) randGen.nextInt(Utility.width()),
            (float) randGen.nextInt(Utility.height()));
        break; // makes one new badger when b or B is pressed
      }
    }
    for (int i = 0; i < badgers.length; i++) {
      if (badgers[i] != null) {
        if (isMouseOver(badgers[i])) {
          if (Utility.key() == 'R' || Utility.key() == 'r') {
            badgers[i] = null;
            break; // deletes lowest index badger under mouse
          }
        }
      }
    }
  }
}

