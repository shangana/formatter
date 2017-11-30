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
package org.wisdom.tool.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.wisdom.tool.constant.FormatConst;

/** 
 * @ClassName: FormatView 
 * @Description: Formatter view frame
*  @Author: Dom Wang
*  @Email: wisdomtool@outlook.com
 * @date 2017-07-22 PM 10:42:57  
 * @version 1.0 
 */
public class FormatView extends JPanel
{
    private static final long serialVersionUID = 957993921065702646L;

    private static FormatView view = null;
    
    private FormatTxtPanel pnlTxt = null;
    
    private JTextField txtFldStat = null;
    
    public FormatView()
    {
        this.init();
    }

    public static FormatView getView()
    {
        if (null == view)
        {
            view = new FormatView();
        }
        return view;
    }

    public JTextField getStat()
    {
        return txtFldStat;
    }

    public FormatTxtPanel getTxtPanel()
    {
        return pnlTxt;
    }
    
    /**
    * 
    * @Title: init 
    * @Description: Component Initialization 
    * @param
    * @return void 
    * @throws
     */
    private void init()
    {
        this.setLayout(new BorderLayout(FormatConst.BORDER_WIDTH, FormatConst.BORDER_WIDTH));
        this.setBorder(BorderFactory.createEmptyBorder(FormatConst.BORDER_WIDTH, FormatConst.BORDER_WIDTH, FormatConst.BORDER_WIDTH, FormatConst.BORDER_WIDTH));

        pnlTxt = new FormatTxtPanel();

        txtFldStat = new JTextField(FormatConst.FIELD_SIZE);
        txtFldStat.setEditable(false);

        this.add(pnlTxt, BorderLayout.CENTER);
        this.add(txtFldStat, BorderLayout.SOUTH);

        this.setBorder(BorderFactory.createTitledBorder(null, FormatConst.FORMATTER, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
    }
}
