// 1�� �ּ���

/*

    ������
    �ּ���

*/


/**
    ����ȭ �ּ�
    ==> � Ŭ������ � �޼ҵ� ���� �� javadoc �� �̿��ؼ�  API ������ ���� �� ����Ѵ�.
*/




// � ���α׷� (��: ���θ� ���α׷�)�� �����Ϸ��� ���� ���赵���� �־�� �ϴµ� 
// �ڹ�(java)��� ���α׷� ���� �� ����Ͽ� ����� ���赵���� "Ŭ����"��� �θ���.

// === *** CLASS(Ŭ����)�� ���� *** ===
/*
    1. ��Ű�� ����
    ==> ��Ű���� Ŭ������ ����Ǿ��� ���丮 ��ζ�� ���� �ȴ�.

    package ��Ű����; ==> �̶� ��Ű������ �ݵ�� �ҹ��ڷ� �����ؾ� �Ѵ�. ���� X

    ��> packge my.day01;
    
    2. import ��
    
      ��> import ��Ű����.Ŭ������;
          
            import java.lang.System;
            import java.lang.StringBuilder;

            import java.lang.*; // * �� ���� ������ �ǹ��Ѵ�. 
 
            import java.util.Date;

     �⺻������ import java.lang.*; �� �����Ǿ��� �ִ�.

      3. Ŭ���� ����

      4. ������(comphile) �ϱ� 
         ==> ��޾� ���޾��� �ٲپ��ִ� ������ ������(comphile) �̶�� �θ���.
         ��޾���? ���(������)�� �˾� �� �� �ִ� ��� 
         ���޾���? ���(��ǻ��)�� �˾Ƶ��� �� �ִ� ���

         ���(��ǻ��)�� ������ ������ �۵��ȴ�.
         ����� +, - �� �̷���� �ִ�.
         ����� ���� 2������ �Ǿ��� �� ==> ������(Binary digit)
       
         A �� ��Ÿ������ �ϴµ� ASCII �ڵ忡 ���� 10���� 65 ��� ��� �صξ����Ƿ� 2���� 0100 0001 �� ��������.

ASCII(�ƽ�Ű)�� American Standard Code for Information Interchange�� ���ڷν�, ANSI(�̱�ǥ����ȸ)���� ���� ǥ�� �ڵ� ü���.

         0, 1 (������ Binary digit, bit)

         �����Ϸ� ==> C:\SW\jdk-17.0.0.1\bin\javac.exe

         C:\NCS\workspace_java>javac HelloExam.java

         5. �����ϱ� ==> C:\SW\jdk-17.0.0.1\bin\javac.exe        
             C:\NCS\workspace_java>java HelloExam
             Hello World!
             �ȳ��ϼ���?

*/


// import java.lang.System;
// �⺻������ import java.lang.*; �� �����Ǿ��� �ִ�.
// import java.lang.*;

import java.util.Date;

public class HelloExam // Ŭ�������� ù���ڴ� �빮�ڷ� �ؾ� �Ѵ�.
                                // ���� ���� �� ���ϸ��� Ŭ������� �����ϰ� �ؾ� �ϸ�, Ȯ���ڴ� .java �̴�.
{ // class body (Ŭ���� ��ü)�� { �� �����ؼ� } �� ������.


   public static void main(String[] args) {
        System.out.println("Hello World!"); // System.out �� ����� (ȭ��)��� ���� �ȴ�.

        System.out.println("�ȳ��ϼ���?");

        System.out.println("������ ���� ù ���Դϴ�.");

        Date now = new Date();
        System.out.println("����ð� : " + now);

   }
}








