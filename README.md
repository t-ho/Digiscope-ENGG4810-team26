# Setup Digiscope in Eclipse

## Install Eclipse IDE
- Install [Java SE Development Kit 8 - JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

- Install [Eclipse IDE](https://eclipse.org/downloads/).

## Import Digiscope project into Eclipse IDE
1 Open Eclipse IDE

![Screenshot 1](http://i.imgur.com/HroQgMD.png)

2 In Eclipse, choose `File > Import`. Then in the pop-up window(below), choose `Projects from Git` under `Git` category, click `Next`.

![Screenshot 2](http://i.imgur.com/kss0tKf.png)

3 In the next window, select `Clone URI`, then click `Next`.

![Screenshot 3](http://i.imgur.com/VQarYv0.png)

4 In the location panel, copy the repository `https://source.eait.uq.edu.au/git/engg4810-team26` into URI field as figure below. In the Authentication panel, provide the username and password. Then click `Next`.

![Screenshot 4](http://i.imgur.com/XbxlKvg.png)

5 In `Brand selection` window, choose `master` brand.

![Screenshot 5](http://i.imgur.com/UiPiqBd.png)

6 Next, choose local destination for project.

![Screenshot 6](http://i.imgur.com/oqDqv82.png)

7 Select `Import existing Eclipse projects`, then click `Next`.

![Screenshot 7](http://i.imgur.com/zhel54v.png)

8 In the `Import Projects` window, select `digiscope` project as follow. Then click `Finish`.

![Screenshot 8](http://i.imgur.com/Rymbrub.png)

## To launch the application
On the left panel `Project Explorer`, Right click on project `digiscope`, choose `Run As` > `Java Application`. In the pop-up window, select `Digiscope - core` as figure below, then click `Ok`.

![Screenshot 9](http://i.imgur.com/7jA2aJu.png)


# Java style guidelines
The source code is follow the [Java Programming Style Guidelines](http://geosoft.no/development/javastyle.html) from Geotechnical Sofware Services.


# Firmware

## Environment Setup

Install Code Composer Studio
http://processors.wiki.ti.com/index.php/Download_CCS

Open the App Center (`Help > CCS App Center`) and install:

1. TI-RTOS for TivaC
2. TI ARM Compiler
3. TivaWare for C Series Software (Complete)

You will need to sign up to the Texas Instruments website in order to download the packages.

Install the latest XDCTools from
http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/rtsc/

## Clone Repository
The repository can be cloned in a number of ways. If you followed the software setup guidelines above, you will already have a copy. If not, the same process can be followed.

Alternatively, use the command:
```bash
git clone https://source.eait.uq.edu.au/git/engg4810-team26
```

## Import Project

Open Code Composer Studio and open the import menu (`File > Import... `).

Select the (`Code Composer Studio > CCS Projects `) source.

Browse to the directory where you cloned the repository.

Select `oscilloscope-firmware` from the discovered projects list and untick the `Copy projects into workspace` option, then click finish.

After the import is complete you should be able to compile and program the board by clicking the debug button.
