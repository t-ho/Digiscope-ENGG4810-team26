# Setup Digiscope in Eclipse
## Clone Git repository
You can clone the repository wherever you want. I like to keep it in `~/dev/eclipse/engg4810-team26`
```bash
git clone https://source.eait.uq.edu.au/git/engg4810-team26
```

## Import Digiscope project into Eclipse
In Eclipse, choose `File > Import`.

![Screenshot 1](http://i.imgur.com/OXgGYa8.png)

In the pop-up window(above), choose `Existing Projects into Workspace` under `General` category, click `Next`.  

![Screenshot 2](http://i.imgur.com/Vh3pfek.png)

Then choose `Select root directory`, browse to `digiscope` directory in the `engg4810-team26` repository, click `Finish`.

# Setup firmware

## Environment Setup

Install Code Composer Studio
http://processors.wiki.ti.com/index.php/Download_CCS

Open the App Center (`Help > CCS App Center`) and install:

1. TI-RTOS for TivaC
2. TivaWare
3. TI ARM Compiler (may be included by default)
