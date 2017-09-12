
package acikanahtarlasifreleme;


import java.security.*;
import javax.crypto.*;

public class AcikAnahtarlaSifreleme {

    public static void main(String[] args)throws Exception
            {
        String plaintext="deniz";//acik metin
        String algoritma="RSA";//kullanacagimiz algoritma
        
        KeyPair anahtar=KeyPairGenerator.getInstance(algoritma).generateKeyPair();//anahtar uretmek icin
        
        PrivateKey hususiAnahtar=anahtar.getPrivate();//acik anahtar
        PublicKey umumiAnahtar=anahtar.getPublic();//gizli anahtar
        
        System.out.println("Anahtar Bilgileri :n"+hususiAnahtar+"nn");//acik anahtari ekrana yazdiriyoruz
        System.out.println("Orjinal metin:"+ByteToString(plaintext.getBytes()));//orjinal metnimizi ekrana yazdiriyoruz
        Cipher cipher=Cipher.getInstance(algoritma);//algoritmayi sifrelemek ve desifre etmek icin
        cipher.init(Cipher.ENCRYPT_MODE, hususiAnahtar);//sifreleme moduna baslamak icin kullanilir
        byte[] sifreliMetin=cipher.doFinal(plaintext.getBytes()); //her doFinal methodundan sonra sifirlanir
        System.out.println("Sifrelenmis metin: "+ByteToString(sifreliMetin));//sifrelenmis metin ekrana yazdirilir
        cipher.init(Cipher.DECRYPT_MODE, umumiAnahtar);//desifre moduna baslamak icin kullanilir
        byte[] desifreliMetin=cipher.doFinal(sifreliMetin);//her doFinal methodu cagirildiginda sifirlanir
        System.out.println("Desifrelenmis metin: "+ByteToString(desifreliMetin));//desifre edilmis metin ekrana yazdirilir
        
    }

    private static String ByteToString(byte[] buffer)
    {
        StringBuilder m=new StringBuilder();
        for(byte b:buffer){
            String temp=Integer.toHexString(0x00FF&b);//16 lik tabandaki degere donusturmeyi saglar
            if(temp.length()==1)
            {
                m.append("0"+temp);//append modu String birlestirmeyi saglar
            }
            else
            {
               m.append(temp);
            }
        }
        return m.toString();
        
    }
    
}
