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
package org.wisdom.tool.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.wisdom.tool.constant.FormatConst;
import org.wisdom.tool.gui.FormatView;
import org.wisdom.tool.model.Charsets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.SourceFormatter;

/** 
* @ClassName: FormatUtil 
* @Description: Format utility 
* @Author: Dom Wang
* @Email: witpool@outlook.com 
* @Date: 2017-07-22 PM 10:42:57 
* @Version: 1.0 
*/
public class FormatUtil
{

    /**
    * 
    * @Title: isJson 
    * @Description: Check if it is JSON string 
    * @param @param content
    * @param @return 
    * @return boolean
    * @throws
     */
    public static boolean isJson(String json)
    {
        if (StringUtils.isEmpty(json))
        {
            return false;
        }

        if (!StringUtils.contains(json, "{"))
        {
            return false;
        }

        JsonParser p = new JsonParser();
        try
        {
            p.parse(json);
        }
        catch(JsonSyntaxException e)
        {
            return false;
        }

        return true;
    }

    /**
    * 
    * @Title: isXml 
    * @Description: Check if it is XML string  
    * @param @param xml
    * @param @return 
    * @return boolean
    * @throws
     */
    public static boolean isXml(String xml)
    {
        if (StringUtils.isEmpty(xml))
        {
            return false;
        }

        try
        {
            DocumentHelper.parseText(xml);
        }
        catch(DocumentException e)
        {
            return false;
        }

        return true;
    }

    /**
    * 
    * @Title: isHtml 
    * @Description: Check if it is HTML string 
    * @param @param html
    * @param @return 
    * @return boolean
    * @throws
     */
    public static boolean isHtml(String html)
    {
        if (StringUtils.isEmpty(html))
        {
            return false;
        }

        if (StringUtils.containsIgnoreCase(html, FormatConst.HTML_LABEL))
        {
            return true;
        }

        return false;
    }

    /**
    * 
    * @Title: prettyJson 
    * @Description: Pretty JSON formatter 
    * @param @param json
    * @param @return 
    * @return String
    * @throws
     */
    public static String prettyJson(String json)
    {
        if (StringUtils.isBlank(json))
        {
            return StringUtils.EMPTY;
        }
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(json);
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        String prettyJson = gson.toJson(je);
        return prettyJson;
    }

    /**
    * 
    * @Title: prettyXml 
    * @Description: Pretty XML formatter  
    * @param @param xml
    * @param @return 
    * @return String
    * @throws
     */
    public static String prettyXml(String xml)
    {
        XMLWriter xw = null;

        if (StringUtils.isBlank(xml))
        {
            return StringUtils.EMPTY;
        }

        try
        {
            Document doc = DocumentHelper.parseText(xml);

            // Format
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding(Charsets.UTF_8.getCname());
            format.setIndentSize(FormatConst.INDENT_SIZE);

            // Writer
            StringWriter sw = new StringWriter();
            xw = new XMLWriter(sw, format);
            xw.write(doc);
            return sw.toString();
        }
        catch(Exception e)
        {
            FormatView.getView().getStat().setText("Failed to format xml: " + e.getMessage());
        } 
        finally
        {
            close(xw);
        }

        return StringUtils.EMPTY;
    }

    /**
    * 
    * @Title: prettyHtml 
    * @Description: Pretty HTML formatter   
    * @param @param html
    * @param @return 
    * @return String
    * @throws
     */
    public static String prettyHtml(String html)
    {
        if (StringUtils.isBlank(html))
        {
            return StringUtils.EMPTY;
        }

        try
        {
            // Writer
            StringWriter sw = new StringWriter();
            new SourceFormatter(new Source(html))
            .setIndentString("    ")
            .setTidyTags(true)
            .setCollapseWhiteSpace(true)
            .writeTo(sw);
            return sw.toString();
        }
        catch(Exception e)
        {
            FormatView.getView().getStat().setText("Failed to format html: " + e.getMessage());
        } 

        return StringUtils.EMPTY;
    }

    /**
    * 
    * @Title: format 
    * @Description: Format text 
    * @param @param txt
    * @param @return 
    * @return String
    * @throws
     */
    public static String format(String txt)
    {
        if (StringUtils.isBlank(txt))
        {
            return StringUtils.EMPTY;
        }

        try
        {
            if (isJson(txt))
            {
                return prettyJson(txt);
            }

            if (isHtml(txt))
            {
                return prettyHtml(txt);
            }

            if (isXml(txt))
            {
                return prettyXml(txt);
            }
        }
        catch(Throwable e)
        {
            FormatView.getView().getStat().setText("Failed to format text: " + e.getMessage());
        }

        return txt;
    }

    /**
    * 
    * @Title: lines 
    * @Description: Get lines 
    * @param @param num
    * @param @return 
    * @return String
    * @throws
     */
    public static String lines(int num)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++)
        {
            sb.append("\r\n");
        }
        return sb.toString();
    }
    
    /**
    * 
    * @Title: getInputStream 
    * @Description: get input stream 
    * @param @param path
    * @param @return 
    * @return InputStream
    * @throws
     */
    public static InputStream getInputStream(String path)
    {
        return FormatUtil.class.getClassLoader().getResourceAsStream(path);
    }

    /**
    * 
    * @Title: close 
    * @Description: Close input stream 
    * @param @param is 
    * @return void
    * @throws
     */
    public static void close(InputStream is)
    {
        if (null == is)
        {
            return;
        }
        try
        {
            is.close();
            is = null;
        }
        catch(IOException e)
        {
            FormatView.getView().getStat().setText("Failed to close input stream: " + e.getMessage());
        }
    }
    
    /**
    * 
    * @Title: close 
    * @Description: Close writer 
    * @param @param w 
    * @return void
    * @throws
     */
    public static void close(XMLWriter xw)
    {
        if (null == xw)
        {
            return;
        }
        try
        {
            xw.close();
            xw = null;
        }
        catch(IOException e)
        {
            FormatView.getView().getStat().setText("Failed to close writer: " + e.getMessage());
        }
    }
    
    /**
    * 
    * @Title: getImage 
    * @Description: Get image from class path
    * @param @param path
    * @param @return
    * @return Image 
    * @throws
     */
    public static Image getImage(String path)
    {
        URL url = FormatUtil.class.getClassLoader().getResource(path);
        return Toolkit.getDefaultToolkit().getImage(url);
    }

    /**
    * 
    * @Title: getIcon
    * @Description: Get icon from class path 
    * @param @param path
    * @param @return
    * @return ImageIcon
    * @throws
     */
    public static ImageIcon getIcon(String path)
    {
        URL url = FormatUtil.class.getClassLoader().getResource(path);
        return new ImageIcon(url);
    }

    /**
    * 
    * @Title: openFile 
    * @Description: open file to get file content 
    * @param @param parent
    * @param @param fc
    * @param @return 
    * @return String
    * @throws
     */
    public static String openFile(Component parent, JFileChooser fc)
    {
        String content = StringUtils.EMPTY;
        int retVal = fc.showOpenDialog(parent);
        if (JFileChooser.APPROVE_OPTION != retVal)
        {
            return content;
        }

        try
        {
            File sf = fc.getSelectedFile();
            content = FileUtils.readFileToString(sf, Charsets.UTF_8.getCname());
        }
        catch(IOException e)
        {
            FormatView.getView().getStat().setText("Failed to read file: " + e.getMessage());
        }

        return content;
    }
    
    /**
    * 
    * @Title: saveFile 
    * @Description: Save HTTP history to file 
    * @param @param parent
    * @param @param fc 
    * @return void
    * @throws
     */
    public static void saveFile(Component parent, JFileChooser fc)
    {
        int retVal = fc.showSaveDialog(parent);
        if (JFileChooser.APPROVE_OPTION != retVal)
        {
            return;
        }

        JTextArea ta = FormatView.getView().getTxtPanel().getTxtAra();
        File sf = fc.getSelectedFile();
        try
        {
            FileUtils.write(sf, ta.getText(), Charsets.UTF_8.getCname());
        }
        catch(IOException e)
        {
            FormatView.getView().getStat().setText("Failed to save contents to file: " + e.getMessage());
        }
    }
    
    /**
    * 
    * @Title: contents 
    * @Description: get file contents
    * @param @return
    * @return String 
    * @throws
     */
    public static String contents(String filename)
    {
        String content = StringUtils.EMPTY;
        try
        {
            InputStream is = FormatUtil.getInputStream(filename);
            content = IOUtils.toString(is, Charsets.UTF_8.getCname());
            FormatUtil.close(is);
        }
        catch(IOException e)
        {
            FormatView.getView().getStat().setText("Failed to read file: " + e.getMessage());
        }
        return content;
    }
    
    /**
    * 
    * @Title: setLocation 
    * @Description: set component's location
    * @param @param c 
    * @return void
    * @throws
     */
    public static void setLocation(Component c)
    {
        int winWidth = c.getWidth();
        int winHeight = c.getHeight();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        c.setLocation(screenWidth / 2 - winWidth / 2, screenHeight / 2 - winHeight / 2);
    }

}
