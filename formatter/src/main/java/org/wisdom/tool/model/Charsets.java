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
package org.wisdom.tool.model;

/** 
* @ClassName: Charsets 
* @Description: Charsets enum
* @Author: Dom Wang
* @Email: witpool@outlook.com 
* @Date: 2017-07-22 PM 10:42:57 
* @Version: 1.0 
*/
public enum Charsets
{
    UTF_8(0, "UTF-8"), 
    US_ASCII(1, "US-ASCII"), 
    ISO_8859_1(2, "ISO-8859-1"), 
    UTF_16(3, "UTF-16"), 
    UTF_16LE(4, "UTF-16LE"), 
    UTF_16BE(5, "UTF-16BE");

    private int cid;

    private String cname;

    private Charsets(int cid, String cname)
    {
        this.cid = cid;
        this.cname = cname;
    }

    public int getCid()
    {
        return cid;
    }

    public void setCid(int cid)
    {
        this.cid = cid;
    }

    public String getCname()
    {
        return cname;
    }

    public void setCname(String cname)
    {
        this.cname = cname;
    }
    
}
