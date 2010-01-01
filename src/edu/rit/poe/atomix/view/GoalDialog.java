/*
 * GoalDialog.java
 *
 * Version:
 *      $Id$
 *
 * Copyright (c) 2009 Peter O. Erickson
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without major
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, and/or distribute copies of, but
 * under no circumstances sublicense and/or sell, the Software,
 * and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package edu.rit.poe.atomix.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 *
 * @author  Peter O. Erickson
 *
 * @version $Id$
 */
public class GoalDialog {
    
    
    public static Dialog getDialog( Activity activity ) {
        AlertDialog.Builder builder = null;
        
        Dialog d = builder.create();
        
        return d;
    }
    
    public static void prepareDialog( Activity activity ) {
        
    }
    
    // @todo make a separate class that can be called statically for drawing
    // the solution panel into the game window
    public static class SolutionView extends View {
        
        public SolutionView( Context context ) {
            super( context );
            
            
            
        }
        
        @Override
        public void onDraw( Canvas canvas ) {
            
            
            
        }
        
        public static void drawGoal( Canvas canvas ) {
            
        }
        
    } // SolutionView
    
} // GoalDialog