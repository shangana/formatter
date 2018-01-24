/* 
 * Copyright 2016-2017 WisdomTool.org. All Rights Reserved.
 * 
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
   
 *  http://www.wisdomtool.org/licenses
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.wisdom.tool;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.wisdom.tool.constant.FormatConst;
import org.wisdom.tool.gui.FormatView;
import org.wisdom.tool.gui.MenuBarView;
import org.wisdom.tool.util.FormatUtil;

/** 
 * @ClassName: FormatMain 
 * @Description: Formatter Main
 * @Author: Dom Wang
 * @Email: witpool@outlook.com 
 * @date 2017-07-22 PM 10:42:57 
 * @version 1.0 
 */
public class FormatMain
{
    /**
    * 
    * @Title: init 
    * @Description: Component Initialization 
    * @param
    * @return void 
    * @throws
     */
    private static void init()
    {
        MenuBarView mbv = new MenuBarView();
        JFrame frame = new JFrame(FormatConst.FORMAT_VERSION);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(mbv.getJMenuBar());
        frame.getContentPane().add(FormatView.getView());
        frame.pack();
        frame.setVisible(true);
        frame.setIconImage(FormatUtil.getImage(FormatConst.LOGO));
        FormatUtil.setLocation(frame);
    }

    /**
    * 
    * @Title: openView 
    * @Description: Open REST view 
    * @param
    * @return void 
    * @throws
     */
    public static void openView()
    {
        init();
    }

    /**
    * 
    * @Title: closeView 
    * @Description: Close REST view  
    * @param  
    * @return void
    * @throws
     */
    public static void closeView()
    {
        System.exit(0);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                FormatMain.openView();
            }
        });
    }

}
