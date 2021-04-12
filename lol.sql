PGDMP         	                y           lol    13.2    13.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    33258    lol    DATABASE     _   CREATE DATABASE lol WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE lol;
                postgres    false            �            1259    33308    administradores    TABLE     �   CREATE TABLE public.administradores (
    id_administrador integer NOT NULL,
    correo character varying(50) NOT NULL,
    usuario character varying(20) NOT NULL,
    password character varying(20) NOT NULL
);
 #   DROP TABLE public.administradores;
       public         heap    postgres    false            �            1259    33297    secuencia_campeones    SEQUENCE     |   CREATE SEQUENCE public.secuencia_campeones
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.secuencia_campeones;
       public          postgres    false            �            1259    33259 	   campeones    TABLE     T  CREATE TABLE public.campeones (
    id_campeon integer DEFAULT nextval('public.secuencia_campeones'::regclass) NOT NULL,
    nombre character varying(50) NOT NULL,
    descripcion character varying(2000) NOT NULL,
    dificultad integer NOT NULL,
    imagen character varying(100),
    posicion character varying(20),
    id_rol integer
);
    DROP TABLE public.campeones;
       public         heap    postgres    false    203            �            1259    33302    secuencia_habilidades    SEQUENCE     ~   CREATE SEQUENCE public.secuencia_habilidades
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.secuencia_habilidades;
       public          postgres    false            �            1259    33269    habilidades    TABLE     +  CREATE TABLE public.habilidades (
    id_habilidad integer DEFAULT nextval('public.secuencia_habilidades'::regclass) NOT NULL,
    nombre_habilidad character varying(50) NOT NULL,
    descripcion_habilidad character varying(200),
    letra_habilidad character varying(10),
    id_campeon integer
);
    DROP TABLE public.habilidades;
       public         heap    postgres    false    204            �            1259    33304    secuencia_roles    SEQUENCE     x   CREATE SEQUENCE public.secuencia_roles
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.secuencia_roles;
       public          postgres    false            �            1259    33264    roles    TABLE     �   CREATE TABLE public.roles (
    id_rol integer DEFAULT nextval('public.secuencia_roles'::regclass) NOT NULL,
    nombre_rol character varying(20) NOT NULL
);
    DROP TABLE public.roles;
       public         heap    postgres    false    205            �          0    33308    administradores 
   TABLE DATA           V   COPY public.administradores (id_administrador, correo, usuario, password) FROM stdin;
    public          postgres    false    206   c!       �          0    33259 	   campeones 
   TABLE DATA           j   COPY public.campeones (id_campeon, nombre, descripcion, dificultad, imagen, posicion, id_rol) FROM stdin;
    public          postgres    false    200   �!       �          0    33269    habilidades 
   TABLE DATA           y   COPY public.habilidades (id_habilidad, nombre_habilidad, descripcion_habilidad, letra_habilidad, id_campeon) FROM stdin;
    public          postgres    false    202   X#       �          0    33264    roles 
   TABLE DATA           3   COPY public.roles (id_rol, nombre_rol) FROM stdin;
    public          postgres    false    201   $       �           0    0    secuencia_campeones    SEQUENCE SET     A   SELECT pg_catalog.setval('public.secuencia_campeones', 2, true);
          public          postgres    false    203            �           0    0    secuencia_habilidades    SEQUENCE SET     D   SELECT pg_catalog.setval('public.secuencia_habilidades', 1, false);
          public          postgres    false    204            �           0    0    secuencia_roles    SEQUENCE SET     >   SELECT pg_catalog.setval('public.secuencia_roles', 1, false);
          public          postgres    false    205            A           2606    33312 $   administradores administradores_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.administradores
    ADD CONSTRAINT administradores_pkey PRIMARY KEY (id_administrador);
 N   ALTER TABLE ONLY public.administradores DROP CONSTRAINT administradores_pkey;
       public            postgres    false    206            7           2606    33263    campeones campeones_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.campeones
    ADD CONSTRAINT campeones_pkey PRIMARY KEY (id_campeon);
 B   ALTER TABLE ONLY public.campeones DROP CONSTRAINT campeones_pkey;
       public            postgres    false    200            =           2606    33273    habilidades habilidades_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.habilidades
    ADD CONSTRAINT habilidades_pkey PRIMARY KEY (id_habilidad);
 F   ALTER TABLE ONLY public.habilidades DROP CONSTRAINT habilidades_pkey;
       public            postgres    false    202            ;           2606    33268    roles roles_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id_rol);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    201            C           2606    33314    administradores unique_correo 
   CONSTRAINT     k   ALTER TABLE ONLY public.administradores
    ADD CONSTRAINT unique_correo UNIQUE (correo) INCLUDE (correo);
 G   ALTER TABLE ONLY public.administradores DROP CONSTRAINT unique_correo;
       public            postgres    false    206    206            9           2606    33301    campeones unique_nombre_campeon 
   CONSTRAINT     m   ALTER TABLE ONLY public.campeones
    ADD CONSTRAINT unique_nombre_campeon UNIQUE (nombre) INCLUDE (nombre);
 I   ALTER TABLE ONLY public.campeones DROP CONSTRAINT unique_nombre_campeon;
       public            postgres    false    200    200            ?           2606    33318 #   habilidades unique_nombre_habilidad 
   CONSTRAINT     �   ALTER TABLE ONLY public.habilidades
    ADD CONSTRAINT unique_nombre_habilidad UNIQUE (nombre_habilidad) INCLUDE (nombre_habilidad);
 M   ALTER TABLE ONLY public.habilidades DROP CONSTRAINT unique_nombre_habilidad;
       public            postgres    false    202    202            E           2606    33316    administradores unique_usuario 
   CONSTRAINT     n   ALTER TABLE ONLY public.administradores
    ADD CONSTRAINT unique_usuario UNIQUE (usuario) INCLUDE (usuario);
 H   ALTER TABLE ONLY public.administradores DROP CONSTRAINT unique_usuario;
       public            postgres    false    206    206            F           2606    33279    campeones campeones_fk_roles    FK CONSTRAINT     �   ALTER TABLE ONLY public.campeones
    ADD CONSTRAINT campeones_fk_roles FOREIGN KEY (id_rol) REFERENCES public.roles(id_rol) NOT VALID;
 F   ALTER TABLE ONLY public.campeones DROP CONSTRAINT campeones_fk_roles;
       public          postgres    false    201    200    2875            G           2606    33289 $   habilidades habilidades_fk_campeones    FK CONSTRAINT     �   ALTER TABLE ONLY public.habilidades
    ADD CONSTRAINT habilidades_fk_campeones FOREIGN KEY (id_campeon) REFERENCES public.campeones(id_campeon) NOT VALID;
 N   ALTER TABLE ONLY public.habilidades DROP CONSTRAINT habilidades_fk_campeones;
       public          postgres    false    202    200    2871            �   7   x�3�LL���s �z����� VfqIQbJ~����	�D��*�"#��=... 5R      �   �  x�MQ�n�0�u_� �� E�J���N�]�����&e�+wi�c��m�	�pfv���ȭ���AW���"ee�:��0�Y�Vi��h5��JOK/qe
�8I��v��8��e����K��_��(ue�U�o8��J+_�Pf����I�n,�����Rb��bߴ��jĵ�fnh���|�V'�G�ndSm�	.+�xN�*X,�^���#}Dp�;m�^�t� �,�¡'���s�+y���D��!�tN��I%D�gD���ƚ��@h2~к�9��;�cE��,U4D>��NF8�tIm���@y�R���U�$C`� �u^����>� e�kF�4x(�]<w�9Kh(�8�KU��z^�����힛�r=���w�"@��$>C.]&~Z�����;��      �   �   x�5���@E�l� CB	D�i$�7/��@����B�
�`,aM<���~��"ߖd9v)���X�Qƨ]�n�KxѴ����g���Ȩ�2�v0��|�f��[i��ˡ��N�B���'Пg�z�z�د�%Lb]XfY���+{�\4�DQ�.�R���CM      �   C   x�3�t,N-���/�2��)M�HL�/J-�2��ML
�p�dA�L9�+��f�!�y��@�=... �g�     