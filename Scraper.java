����   1 �
 ) F	 G H I
 J K L	 G M
  N
  O P
 Q R S T
  F U
  V
  W
 X Y Z [ \ ]
 ^ _
 ` a b c b d e f
  _
 ` g h i j k
  l
  m
  n
  o p q r s t <init> ()V Code LineNumberTable LocalVariableTable this 	LScraper; main ([Ljava/lang/String;)V title Ljava/lang/String; row Lorg/jsoup/nodes/Element; document Lorg/jsoup/nodes/Document; result writer Ljava/io/BufferedWriter; e Ljava/lang/Exception; in Ljava/util/Scanner; url args [Ljava/lang/String; 
Exceptions 
SourceFile Scraper.java * + u v w 3
Enter cppreference.com url (enter 'exit' to end) : x y z java/util/Scanner > { * | } ~ exit  � � terminated. java/lang/StringBuilder You entered :  � � � ~ � � � � � �   table.t-dcl-begin tr.t-dcl � � � � � � � � � � � org/jsoup/nodes/Element span.mw-geshi � ~ 
 java/io/BufferedWriter java/io/FileWriter 
.\test.txt * z * � � z � + &
Sucessfully parsed. Saved as test.txt java/lang/Exception 'Error: Not a valid cppreference.com url Scraper java/lang/Object java/lang/System out Ljava/io/PrintStream; java/io/PrintStream println (Ljava/lang/String;)V Ljava/io/InputStream; (Ljava/io/InputStream;)V nextLine ()Ljava/lang/String; java/lang/String equals (Ljava/lang/Object;)Z append -(Ljava/lang/String;)Ljava/lang/StringBuilder; toString org/jsoup/Jsoup connect *(Ljava/lang/String;)Lorg/jsoup/Connection; org/jsoup/Connection get ()Lorg/jsoup/nodes/Document; org/jsoup/nodes/Document select /(Ljava/lang/String;)Lorg/jsoup/select/Elements; org/jsoup/select/Elements iterator ()Ljava/util/Iterator; java/util/Iterator hasNext ()Z next ()Ljava/lang/Object; text (Ljava/io/Writer;)V write close ! ( )       * +  ,   /     *� �    -       
 .        / 0   	 1 2  ,  �     ܲ � � Y� � L+� M,	� 
� � � � �� � Y� � ,� � � ,� �  N:-� � :�  � A�  � :� � :� � � Y� � � � � :���� Y� Y � !� ":� #� $� %� � N� '� ��(�  E � � &  -   ^           !  )  ,  E  O  S  t  �  �   � ! � $ � % � & � ' � + � ) � * � , � . .   \ 	 � # 3 4  t / 5 6  O } 7 8  S y 9 4  �  : ;  �  < =   � > ?   � @ 4    � A B   C     &  D    E