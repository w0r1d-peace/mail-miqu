package com.islet.mail;

import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

/**
 * @author tangJM.
 * @date 2021/9/29
 * @description
 */
public class MailTest
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            String host = "pop.tom.com";
            String username = "w0r1d_space@tom.com";
            String password = "6yhn7UJM";

            Properties p = new Properties();
            p.setProperty("mail.pop3.host", "pop.tom.com"); // 按需要更改
            p.setProperty("mail.pop3.port", "110");
            // SSL安全连接参数
           /* p.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            p.setProperty("mail.pop3.socketFactory.fallback", "true");
            p.setProperty("mail.pop3.socketFactory.port", "995");
*/
            Session session = Session.getDefaultInstance(p, null);
            Store store = session.getStore("pop3");
            store.connect(host, username, password);

            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            Message message[] = folder.getMessages();
            System.out.println("邮件数量:　" + message.length);
            new GetMailInfoThread(message).start();
        }
        catch (NoSuchProviderException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MessagingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
