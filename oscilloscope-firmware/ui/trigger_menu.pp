




 






 






























 





 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 

    typedef   signed char   int8_t;
    typedef unsigned char  uint8_t;
    typedef          short  int16_t;
    typedef unsigned short uint16_t;
    typedef          int    int32_t;
    typedef unsigned int   uint32_t;


    typedef          long long  int64_t;
    typedef unsigned long long uint64_t;

 

    typedef  int8_t   int_least8_t;
    typedef uint8_t  uint_least8_t;

    typedef  int16_t  int_least16_t;
    typedef uint16_t uint_least16_t;
    typedef  int32_t  int_least32_t;
    typedef uint32_t uint_least32_t;


    typedef  int64_t  int_least64_t;
    typedef uint64_t uint_least64_t;

 

    typedef  int32_t  int_fast8_t;
    typedef uint32_t uint_fast8_t;
    typedef  int32_t  int_fast16_t;
    typedef uint32_t uint_fast16_t;

    typedef  int32_t  int_fast32_t;
    typedef uint32_t uint_fast32_t;


    typedef  int64_t  int_fast64_t;
    typedef uint64_t uint_fast64_t;

 
    typedef          int intptr_t;
    typedef unsigned int uintptr_t;

 
    typedef          long long intmax_t;
    typedef unsigned long long uintmax_t;





 

 
















 







 







 




 



 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 


 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 


#pragma diag_push
#pragma CHECK_MISRA("-19.4")  

 


 
 
 

#pragma diag_pop

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 


#pragma diag_push
#pragma CHECK_MISRA("-19.7")  
#pragma CHECK_MISRA("-19.10")  
#pragma CHECK_MISRA("-20.1")  
#pragma CHECK_MISRA("-20.2")  


typedef struct __va_list {
    void * __ap;
} va_list;






#pragma diag_pop


#pragma diag_push


 
 
#pragma CHECK_MISRA("-19.15")


#pragma diag_pop

 
 
 

 
 
 
 

 
 
 


 
 
 
typedef unsigned size_t;

typedef struct {
      int fd;                     
      unsigned char* buf;         
      unsigned char* pos;         
      unsigned char* bufend;      
      unsigned char* buff_stop;   
      unsigned int   flags;       
} FILE;

typedef long fpos_t;

 
 
 
 
 
 



 
 
 








 


 
 
 

extern  FILE _ftable[10];
extern  char __TI_tmpnams[10][16];

 
 
 
 
 
 
extern  int     remove(const char *_file);
extern  int     rename(const char *_old, const char *_new);
extern  FILE   *tmpfile(void);
extern  char   *tmpnam(char *_s);

 
 
 
extern  int     fclose(FILE *_fp); 
extern  FILE   *fopen(const char *_fname, const char *_mode);
extern  FILE   *freopen(const char *_fname, const char *_mode,
			            register FILE *_fp);
extern  void    setbuf(register FILE *_fp, char *_buf);
extern  int     setvbuf(register FILE *_fp, register char *_buf, 
			            register int _type, register size_t _size);
extern  int     fflush(register FILE *_fp); 

 
 
 
extern  int fprintf(FILE *_fp, const char *_format, ...)
               __attribute__((__format__ (__printf__, 2, 3)));
extern  int fscanf(FILE *_fp, const char *_fmt, ...)
               __attribute__((__format__ (__scanf__, 2, 3)));
extern  int printf(const char *_format, ...)
               __attribute__((__format__ (__printf__, 1, 2)));
extern  int scanf(const char *_fmt, ...)
               __attribute__((__format__ (__scanf__, 1, 2)));
extern  int sprintf(char *_string, const char *_format, ...)
               __attribute__((__format__ (__printf__, 2, 3)));
extern  int snprintf(char *_string, size_t _n, 
				 const char *_format, ...)
               __attribute__((__format__ (__printf__, 3, 4)));
extern  int sscanf(const char *_str, const char *_fmt, ...)
               __attribute__((__format__ (__scanf__, 2, 3)));
extern  int vfprintf(FILE *_fp, const char *_format, va_list _ap)
               __attribute__((__format__ (__printf__, 2, 0)));
extern  int vfscanf(FILE *_fp, const char *_fmt, va_list _ap)
               __attribute__((__format__ (__scanf__, 2, 0)));
extern  int vprintf(const char *_format, va_list _ap)
               __attribute__((__format__ (__printf__, 1, 0)));
extern  int vscanf(const char *_format, va_list _ap)
               __attribute__((__format__ (__scanf__, 1, 0)));
extern  int vsprintf(char *_string, const char *_format,
				 va_list _ap)
               __attribute__((__format__ (__printf__, 2, 0)));
extern  int vsnprintf(char *_string, size_t _n, 
				  const char *_format, va_list _ap)
               __attribute__((__format__ (__printf__, 3, 0)));
extern  int vsscanf(const char *_str, const char *_fmt, va_list _ap)
               __attribute__((__format__ (__scanf__, 2, 0)));

 
 
 
extern  int     fgetc(register FILE *_fp);
extern  char   *fgets(char *_ptr, register int _size,
				  register FILE *_fp);
extern  int     fputc(int _c, register FILE *_fp);
extern  int     fputs(const char *_ptr, register FILE *_fp);
extern  int     getc(FILE *_p);
extern  int     getchar(void);
extern  char   *gets(char *_ptr); 
extern  int     putc(int _x, FILE *_fp);
extern  int     putchar(int _x);
extern  int     puts(const char *_ptr); 
extern  int     ungetc(int _c, register FILE *_fp);

 
 
 
extern  size_t  fread(void *_ptr, size_t _size, size_t _count,
				  FILE *_fp);
extern  size_t  fwrite(const void *_ptr, size_t _size,
				   size_t _count, register FILE *_fp); 

 
 
 
extern  int     fgetpos(FILE *_fp, fpos_t *_pos);
extern  int     fseek(register FILE *_fp, long _offset,
				  int _ptrname);
extern  int     fsetpos(FILE *_fp, const fpos_t *_pos);
extern  long    ftell(FILE *_fp);
extern  void    rewind(register FILE *_fp); 

 
 
 
extern  void    clearerr(FILE *_fp);
extern  int     feof(FILE *_fp);
extern  int     ferror(FILE *_fp);
extern  void    perror(const char *_s);
























































typedef struct
{
    
    
    
    int16_t i16XMin;

    
    
    
    int16_t i16YMin;

    
    
    
    int16_t i16XMax;

    
    
    
    int16_t i16YMax;
}
tRectangle;






typedef struct
{
    
    
    
    int32_t i32Size;

    
    
    
    void *pvDisplayData;

    
    
    
    uint16_t ui16Width;

    
    
    
    uint16_t ui16Height;

    
    
    
    void (*pfnPixelDraw)(void *pvDisplayData, int32_t i32X, int32_t i32Y,
                           uint32_t ui32Value);

    
    
    
    
    
    
    void (*pfnPixelDrawMultiple)(void *pvDisplayData, int32_t i32X,
                                   int32_t i32Y, int32_t i32X0,
                                   int32_t i32Count, int32_t i32BPP,
                                   const uint8_t *pui8Data,
                                   const uint8_t *pui8Palette);

    
    
    
    void (*pfnLineDrawH)(void *pvDisplayData, int32_t i32X1, int32_t i32X2,
                         int32_t i32Y, uint32_t ui32Value);

    
    
    
    void (*pfnLineDrawV)(void *pvDisplayData, int32_t i32X, int32_t i32Y1,
                         int32_t i32Y2, uint32_t ui32Value);

    
    
    
    void (*pfnRectFill)(void *pvDisplayData, const tRectangle *psRect,
                        uint32_t ui32Value);

    
    
    
    
    uint32_t (*pfnColorTranslate)(void *pvDisplayData, uint32_t ui32Value);

    
    
    
    
    void (*pfnFlush)(void *pvDisplayData);
}
tDisplay;



















typedef struct
{
    
    
    
    
    uint8_t ui8Format;

    
    
    
    
    
    uint8_t ui8MaxWidth;

    
    
    
    
    uint8_t ui8Height;

    
    
    
    
    
    uint8_t ui8Baseline;

    
    
    
    uint16_t pui16Offset[96];

    
    
    
    const uint8_t *pui8Data;
}
tFont;















typedef struct
{
    
    
    
    
    uint8_t ui8Format;

    
    
    
    
    
    uint8_t ui8MaxWidth;

    
    
    
    
    uint8_t ui8Height;

    
    
    
    
    
    uint8_t ui8Baseline;

    
    
    
    
    uint8_t ui8First;

    
    
    
    
    uint8_t ui8Last;

    
    
    
    
    const uint16_t *pui16Offset;

    
    
    
    const uint8_t *pui8Data;
}
tFontEx;









































































































typedef struct
{
    
    
    
    
    uint8_t ui8Format;

    
    
    
    
    
    uint8_t ui8MaxWidth;

    
    
    
    
    uint8_t ui8Height;

    
    
    
    
    
    uint8_t ui8Baseline;

    
    
    
    
    uint16_t ui16Codepage;

    
    
    
    
    uint16_t ui16NumBlocks;
}
tFontWide;

typedef struct
{
    
    
    
    
    
    uint32_t ui32StartCodepoint;

    
    
    
    
    
    uint32_t ui32NumCodepoints;

    
    
    
    
    uint32_t ui32GlyphTableOffset;
}
tFontBlock;

typedef struct
{
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    uint32_t ui32GlyphOffset[1];
}
tFontOffsetTable;








typedef struct
{
    
    
    
    
    void (*pfnFontInfoGet)(uint8_t *pui8FontId, uint8_t *pui8Format,
                           uint8_t *pui8Width, uint8_t *pui8Height,
                           uint8_t *pui8Baseline);

    
    
    
    
    
    
    
    
    
    const uint8_t *(*pfnFontGlyphDataGet)(uint8_t *pui8FontId,
                                          uint32_t ui32CodePoint,
                                          uint8_t *pui8Width);

    
    
    
    
    uint16_t (*pfnFontCodepageGet)(uint8_t *pui8FontId);

    
    
    
    
    uint16_t (*pfnFontNumBlocksGet)(uint8_t *pui8FontId);

    
    
    
    
    uint32_t (*pfnFontBlockCodepointsGet)(uint8_t *pui8FontId,
                                            uint16_t ui16BlockIndex,
                                            uint32_t *pui32Start);
}
tFontAccessFuncs;













typedef struct
{
    
    
    
    uint8_t ui8Format;

    
    
    
    
    
    uint8_t *pui8FontId;

    
    
    
    const tFontAccessFuncs *pFuncs;
}
tFontWrapper;



































































































































typedef struct
{
    
    
    
    uint16_t ui16SrcCodepage;

    
    
    
    uint16_t ui16FontCodepage;

    
    
    
    
    uint32_t (*pfnMapChar)(const char *pcSrcChar, uint32_t ui32Count,
                           uint32_t *pui32Skip);
}
tCodePointMap;






struct _tContext;










typedef void (*tStringRenderer)(const struct _tContext *, const char *,
                                int32_t, int32_t, int32_t, _Bool);








typedef struct
{
    
    
    
    
    
    void (*pfnStringRenderer)(const struct _tContext *, const char *, int32_t,
                              int32_t, int32_t, _Bool);

    
    
    
    
    
    
    tCodePointMap *pCodePointMapTable;

    
    
    
    uint16_t ui16Codepage;

    
    
    
    uint8_t ui8NumCodePointMaps;

    
    
    
    uint8_t ui8Reserved;
}
tGrLibDefaults;








typedef struct _tContext
{
    
    
    
    int32_t i32Size;

    
    
    
    const tDisplay *psDisplay;

    
    
    
    tRectangle sClipRegion;

    
    
    
    uint32_t ui32Foreground;

    
    
    
    uint32_t ui32Background;

    
    
    
    const tFont *psFont;

    
    
    
    
    
    void (*pfnStringRenderer)(const struct _tContext *, const char *, int32_t,
                              int32_t, int32_t, _Bool);

    
    
    
    
    const tCodePointMap *pCodePointMapTable;

    
    
    
    uint16_t ui16Codepage;

    
    
    
    uint8_t ui8NumCodePointMaps;

    
    
    
    
    uint8_t ui8CodePointMap;

    
    
    
    uint8_t ui8Reserved;
}
tContext;

























































extern void GrContextFontSet(tContext *psContext, const tFont *pFnt);











































































































































































































































extern const tFont g_sFontCm12;
extern const tFont g_sFontCm12b;
extern const tFont g_sFontCm12i;
extern const tFont g_sFontCm14;
extern const tFont g_sFontCm14b;
extern const tFont g_sFontCm14i;
extern const tFont g_sFontCm16;
extern const tFont g_sFontCm16b;
extern const tFont g_sFontCm16i;
extern const tFont g_sFontCm18;
extern const tFont g_sFontCm18b;
extern const tFont g_sFontCm18i;
extern const tFont g_sFontCm20;
extern const tFont g_sFontCm20b;
extern const tFont g_sFontCm20i;
extern const tFont g_sFontCm22;
extern const tFont g_sFontCm22b;
extern const tFont g_sFontCm22i;
extern const tFont g_sFontCm24;
extern const tFont g_sFontCm24b;
extern const tFont g_sFontCm24i;
extern const tFont g_sFontCm26;
extern const tFont g_sFontCm26b;
extern const tFont g_sFontCm26i;
extern const tFont g_sFontCm28;
extern const tFont g_sFontCm28b;
extern const tFont g_sFontCm28i;
extern const tFont g_sFontCm30;
extern const tFont g_sFontCm30b;
extern const tFont g_sFontCm30i;
extern const tFont g_sFontCm32;
extern const tFont g_sFontCm32b;
extern const tFont g_sFontCm32i;
extern const tFont g_sFontCm34;
extern const tFont g_sFontCm34b;
extern const tFont g_sFontCm34i;
extern const tFont g_sFontCm36;
extern const tFont g_sFontCm36b;
extern const tFont g_sFontCm36i;
extern const tFont g_sFontCm38;
extern const tFont g_sFontCm38b;
extern const tFont g_sFontCm38i;
extern const tFont g_sFontCm40;
extern const tFont g_sFontCm40b;
extern const tFont g_sFontCm40i;
extern const tFont g_sFontCm42;
extern const tFont g_sFontCm42b;
extern const tFont g_sFontCm42i;
extern const tFont g_sFontCm44;
extern const tFont g_sFontCm44b;
extern const tFont g_sFontCm44i;
extern const tFont g_sFontCm46;
extern const tFont g_sFontCm46b;
extern const tFont g_sFontCm46i;
extern const tFont g_sFontCm48;
extern const tFont g_sFontCm48b;
extern const tFont g_sFontCm48i;
extern const tFont g_sFontCmsc12;
extern const tFont g_sFontCmsc14;
extern const tFont g_sFontCmsc16;
extern const tFont g_sFontCmsc18;
extern const tFont g_sFontCmsc20;
extern const tFont g_sFontCmsc22;
extern const tFont g_sFontCmsc24;
extern const tFont g_sFontCmsc26;
extern const tFont g_sFontCmsc28;
extern const tFont g_sFontCmsc30;
extern const tFont g_sFontCmsc32;
extern const tFont g_sFontCmsc34;
extern const tFont g_sFontCmsc36;
extern const tFont g_sFontCmsc38;
extern const tFont g_sFontCmsc40;
extern const tFont g_sFontCmsc42;
extern const tFont g_sFontCmsc44;
extern const tFont g_sFontCmsc46;
extern const tFont g_sFontCmsc48;
extern const tFont g_sFontCmss12;
extern const tFont g_sFontCmss12b;
extern const tFont g_sFontCmss12i;
extern const tFont g_sFontCmss14;
extern const tFont g_sFontCmss14b;
extern const tFont g_sFontCmss14i;
extern const tFont g_sFontCmss16;
extern const tFont g_sFontCmss16b;
extern const tFont g_sFontCmss16i;
extern const tFont g_sFontCmss18;
extern const tFont g_sFontCmss18b;
extern const tFont g_sFontCmss18i;
extern const tFont g_sFontCmss20;
extern const tFont g_sFontCmss20b;
extern const tFont g_sFontCmss20i;
extern const tFont g_sFontCmss22;
extern const tFont g_sFontCmss22b;
extern const tFont g_sFontCmss22i;
extern const tFont g_sFontCmss24;
extern const tFont g_sFontCmss24b;
extern const tFont g_sFontCmss24i;
extern const tFont g_sFontCmss26;
extern const tFont g_sFontCmss26b;
extern const tFont g_sFontCmss26i;
extern const tFont g_sFontCmss28;
extern const tFont g_sFontCmss28b;
extern const tFont g_sFontCmss28i;
extern const tFont g_sFontCmss30;
extern const tFont g_sFontCmss30b;
extern const tFont g_sFontCmss30i;
extern const tFont g_sFontCmss32;
extern const tFont g_sFontCmss32b;
extern const tFont g_sFontCmss32i;
extern const tFont g_sFontCmss34;
extern const tFont g_sFontCmss34b;
extern const tFont g_sFontCmss34i;
extern const tFont g_sFontCmss36;
extern const tFont g_sFontCmss36b;
extern const tFont g_sFontCmss36i;
extern const tFont g_sFontCmss38;
extern const tFont g_sFontCmss38b;
extern const tFont g_sFontCmss38i;
extern const tFont g_sFontCmss40;
extern const tFont g_sFontCmss40b;
extern const tFont g_sFontCmss40i;
extern const tFont g_sFontCmss42;
extern const tFont g_sFontCmss42b;
extern const tFont g_sFontCmss42i;
extern const tFont g_sFontCmss44;
extern const tFont g_sFontCmss44b;
extern const tFont g_sFontCmss44i;
extern const tFont g_sFontCmss46;
extern const tFont g_sFontCmss46b;
extern const tFont g_sFontCmss46i;
extern const tFont g_sFontCmss48;
extern const tFont g_sFontCmss48b;
extern const tFont g_sFontCmss48i;
extern const tFont g_sFontCmtt12;
extern const tFont g_sFontCmtt14;
extern const tFont g_sFontCmtt16;
extern const tFont g_sFontCmtt18;
extern const tFont g_sFontCmtt20;
extern const tFont g_sFontCmtt22;
extern const tFont g_sFontCmtt24;
extern const tFont g_sFontCmtt26;
extern const tFont g_sFontCmtt28;
extern const tFont g_sFontCmtt30;
extern const tFont g_sFontCmtt32;
extern const tFont g_sFontCmtt34;
extern const tFont g_sFontCmtt36;
extern const tFont g_sFontCmtt38;
extern const tFont g_sFontCmtt40;
extern const tFont g_sFontCmtt42;
extern const tFont g_sFontCmtt44;
extern const tFont g_sFontCmtt46;
extern const tFont g_sFontCmtt48;
extern const tFont g_sFontFixed6x8;










































































































































































































extern void GrLibInit(const tGrLibDefaults *pDefaults);
extern void GrCircleDraw(const tContext *psContext, int32_t i32X, int32_t i32Y,
                         int32_t i32Radius);
extern void GrCircleFill(const tContext *psContext, int32_t i32X, int32_t i32Y,
                         int32_t i32Radius);
extern void GrContextClipRegionSet(tContext *psContext, tRectangle *psRect);
extern void GrContextInit(tContext *psContext, const tDisplay *psDisplay);
extern void GrImageDraw(const tContext *psContext,
                        const uint8_t *pui8Image, int32_t i32X, int32_t i32Y);
extern void GrTransparentImageDraw(const tContext *psContext,
                                   const uint8_t *pui8Image,
                                   int32_t i32X, int32_t i32Y,
                                   uint32_t ui32Transparent);
extern void GrLineDraw(const tContext *psContext, int32_t i32X1, int32_t i32Y1,
                       int32_t i32X2, int32_t i32Y2);
extern void GrLineDrawH(const tContext *psContext, int32_t i32X1, int32_t i32X2,
                        int32_t i32Y);
extern void GrLineDrawV(const tContext *psContext, int32_t i32X, int32_t i32Y1,
                        int32_t i32Y2);
extern void GrOffScreen1BPPInit(tDisplay *psDisplay, uint8_t *pui8Image,
                                int32_t i32Width, int32_t i32Height);
extern void GrOffScreen4BPPInit(tDisplay *psDisplay, uint8_t *pui8Image,
                                int32_t i32Width, int32_t i32Height);
extern void GrOffScreen4BPPPaletteSet(tDisplay *psDisplay,
                                      uint32_t *pui32Palette,
                                      uint32_t ui32Offset,
                                      uint32_t ui32Count);
extern void GrOffScreen8BPPInit(tDisplay *psDisplay, uint8_t *pui8Image,
                                int32_t i32Width, int32_t i32Height);
extern void GrOffScreen8BPPPaletteSet(tDisplay *psDisplay,
                                      uint32_t *pui32Palette,
                                      uint32_t ui32Offset,
                                      uint32_t ui32Count);
extern void GrRectDraw(const tContext *psContext, const tRectangle *psRect);
extern void GrRectFill(const tContext *psContext, const tRectangle *psRect);
extern void GrStringDraw(const tContext *psContext, const char *pcString,
                         int32_t i32Length, int32_t i32X, int32_t i32Y,
                         uint32_t bOpaque);
extern int32_t GrStringWidthGet(const tContext *psContext, const char *pcString,
                                int32_t i32Length);
extern void GrStringTableSet(const void *pvTable);
uint32_t GrStringLanguageSet(uint16_t ui16LangID);
uint32_t GrStringGet(int32_t i32Index, char *pcData, uint32_t ui32Size);
extern int32_t GrRectOverlapCheck(tRectangle *psRect1, tRectangle *psRect2);
extern int32_t GrRectIntersectGet(tRectangle *psRect1, tRectangle *psRect2,
                                  tRectangle *psIntersect);






extern void GrFontInfoGet(const tFont *psFont, uint8_t *pui8Format,
                          uint8_t *pui8MaxWidth, uint8_t *pui8Height,
                          uint8_t *pui8Baseline);
extern uint32_t GrFontMaxWidthGet(const tFont *psFont);
extern uint32_t GrFontHeightGet(const tFont *psFont);
extern uint32_t GrFontBaselineGet(const tFont *psFont);
extern const uint8_t *GrFontGlyphDataGet(const tFont *psFont,
                                         uint32_t ui32CodePoint,
                                         uint8_t *pui8Width);
extern uint16_t GrFontCodepageGet(const tFont *psFont);
extern uint16_t GrFontNumBlocksGet(const tFont *psFont);
extern uint32_t GrFontBlockCodepointsGet(const tFont *psFont,
                                         uint16_t ui16BlockIndex,
                                         uint32_t *pui32Start);






void GrFontGlyphRender(const tContext *psContext, const uint8_t *pui8Data,
                       int32_t i32X, int32_t i32Y, _Bool bCompressed,
                       _Bool bOpaque);
void
GrDefaultStringRenderer(const tContext *psContext, const char *pcString,
                        int32_t i32Length, int32_t i32X, int32_t i32Y,
                        _Bool bOpaque);






extern void GrCodepageMapTableSet(tContext *psContext,
                                  tCodePointMap *pCodePointMapTable,
                                  uint8_t ui8NumMaps);
extern int32_t GrStringCodepageSet(tContext *psContext,
                                   uint16_t ui16Codepage);
extern uint32_t GrStringRendererSet(tContext *psContext,
                                    tStringRenderer pfnRenderer);
extern uint32_t GrStringNumGlyphsGet(const tContext *psContext,
                                     const char *pcString);
extern uint32_t GrStringNextCharGet(const tContext *psContext,
                                    const char *pcString,
                                    uint32_t ui32Count,
                                    uint32_t *pui32Skip);
extern uint32_t GrMapUnicode_Unicode(const char *pcSrcChar,
                                     uint32_t ui32Count,
                                     uint32_t *pui32Skip);
extern uint32_t GrMapUTF8_Unicode(const char *pcSrcChar,
                                  uint32_t ui32Count,
                                  uint32_t *pui32Skip);
extern uint32_t GrMapUTF16LE_Unicode(const char *pcSrcChar,
                                     uint32_t ui32Count,
                                     uint32_t *pui32Skip);
extern uint32_t GrMapUTF16BE_Unicode(const char *pcSrcChar,
                                     uint32_t ui32Count,
                                     uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_1_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_2_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_3_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_4_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_5_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_6_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_7_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_8_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_9_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_10_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_11_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_13_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_14_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_15_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapISO8859_16_Unicode(const char *pcSrcChar,
                                       uint32_t ui32Count,
                                       uint32_t *pui32Skip);
extern uint32_t GrMapWIN1250_Unicode(const char *pcSrcChar,
                                     uint32_t ui32Count,
                                     uint32_t *pui32Skip);
extern uint32_t GrMapWIN1251_Unicode(const char *pcSrcChar,
                                     uint32_t ui32Count,
                                     uint32_t *pui32Skip);
extern uint32_t GrMapWIN1252_Unicode(const char *pcSrcChar,
                                     uint32_t ui32Count,
                                     uint32_t *pui32Skip);
extern uint32_t GrMapWIN1253_Unicode(const char *pcSrcChar,
                                     uint32_t ui32Count,
                                     uint32_t *pui32Skip);
extern uint32_t GrMapWIN1254_Unicode(const char *pcSrcChar,
                                     uint32_t ui32Count,
                                     uint32_t *pui32Skip);







































































typedef struct __Widget
{
    
    
    
    
    int32_t i32Size;

    
    
    
    struct __Widget *psParent;

    
    
    
    struct __Widget *psNext;

    
    
    
    struct __Widget *psChild;

    
    
    
    const tDisplay *psDisplay;

    
    
    
    tRectangle sPosition;

    
    
    
    int32_t (*pfnMsgProc)(struct __Widget *psWidget, uint32_t ui32Message,
                          uint32_t ui32Param1, uint32_t ui32Param2);
}
tWidget;















































































































extern tWidget g_sRoot;
extern int32_t WidgetDefaultMsgProc(tWidget *psWidget, uint32_t ui32Message,
                                    uint32_t ui32Param1, uint32_t ui32Param2);
extern void WidgetAdd(tWidget *psParent, tWidget *psWidget);
extern void WidgetRemove(tWidget *psWidget);
extern uint32_t WidgetMessageSendPreOrder(tWidget *psWidget,
                                          uint32_t ui32Message,
                                          uint32_t ui32Param1,
                                          uint32_t ui32Param2,
                                          _Bool bStopOnSuccess);
extern uint32_t WidgetMessageSendPostOrder(tWidget *psWidget,
                                           uint32_t ui32Message,
                                           uint32_t ui32Param1,
                                           uint32_t ui32Param2,
                                           _Bool bStopOnSuccess);
extern int32_t WidgetMessageQueueAdd(tWidget *psWidget, uint32_t ui32Message,
                                     uint32_t ui32Param1,
                                     uint32_t ui32Param2,
                                     _Bool bPostOrder,
                                     _Bool bStopOnSuccess);
extern void WidgetMessageQueueProcess(void);
extern int32_t WidgetPointerMessage(uint32_t ui32Message, int32_t i32X,
                                    int32_t i32Y);
extern void WidgetMutexInit(uint8_t *pi8Mutex);
extern uint32_t WidgetMutexGet(uint8_t *pi8Mutex);
extern void WidgetMutexPut(uint8_t *pi8Mutex);


























































typedef struct
{
    
    
    
    tWidget sBase;

    
    
    
    
    uint32_t ui32Style;

    
    
    
    
    
    uint32_t ui32FillColor;

    
    
    
    
    uint32_t ui32OutlineColor;

    
    
    
    
    uint32_t ui32TextColor;

    
    
    
    
    const tFont *psFont;

    
    
    
    
    const char *pcText;

    
    
    
    
    const uint8_t *pui8Image;

    
    
    
    
    void (*pfnOnPaint)(tWidget *psWidget, tContext *psContext);
}
tCanvasWidget;



















































































































































































































































































































































































































































































































extern int32_t CanvasMsgProc(tWidget *psWidget, uint32_t ui32Msg,
                             uint32_t ui32Param1, uint32_t ui32Param2);
extern void CanvasInit(tCanvasWidget *psWidget, const tDisplay *psDisplay,
                       int32_t i32X, int32_t i32Y, int32_t i32Width,
                       int32_t i32Height);


























































typedef struct
{
    
    
    
    tWidget sBase;

    
    
    
    
    uint32_t ui32Style;

    
    
    
    
    
    uint32_t ui32FillColor;

    
    
    
    
    
    uint32_t ui32PressFillColor;

    
    
    
    
    uint32_t ui32OutlineColor;

    
    
    
    
    uint32_t ui32TextColor;

    
    
    
    
    const tFont *psFont;

    
    
    
    
    const char *pcText;

    
    
    
    
    const uint8_t *pui8Image;

    
    
    
    
    const uint8_t *pui8PressImage;

    
    
    
    
    
    
    uint16_t ui16AutoRepeatDelay;

    
    
    
    
    
    
    uint16_t ui16AutoRepeatRate;

    
    
    
    
    uint32_t ui32AutoRepeatCount;

    
    
    
    
    void (*pfnOnClick)(tWidget *psWidget);
}
tPushButtonWidget;
























































































































































































































































































































































































































































































































































































































































extern int32_t RectangularButtonMsgProc(tWidget *psWidget, uint32_t ui32Msg,
                                        uint32_t ui32Param1,
                                        uint32_t ui32Param2);
extern void RectangularButtonInit(tPushButtonWidget *psWidget,
                                  const tDisplay *psDisplay, int32_t i32X,
                                  int32_t i32Y, int32_t i32Width,
                                  int32_t i32Height);
extern int32_t CircularButtonMsgProc(tWidget *psWidget, uint32_t ui32Msg,
                                     uint32_t ui32Param1,
                                     uint32_t ui32Param2);
extern void CircularButtonInit(tPushButtonWidget *psWidget,
                               const tDisplay *psDisplay, int32_t i32X,
                               int32_t i32Y, int32_t i32R);




















 



extern void SSD1289_Init(void);
extern void Pant(uint16_t color);
extern void SSD1289_Backlight_Set(uint8_t level);
extern void SSD1289_Set_Backlight_On(_Bool state);
extern _Bool SSD1289_Get_Backlight_On(void);

extern const tDisplay SSD1289_Display;






 

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 




 





 















 




 








 












 


 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 


#pragma diag_push


 
 
#pragma CHECK_MISRA("-19.15")


#pragma diag_pop
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 


#pragma diag_push
#pragma CHECK_MISRA("-19.7")  
#pragma CHECK_MISRA("-20.1")  
#pragma CHECK_MISRA("-20.2")  



typedef int ptrdiff_t;



typedef unsigned short wchar_t;


#pragma diag_push
#pragma CHECK_MISRA("-19.10")  



#pragma diag_pop



#pragma diag_push
#pragma CHECK_MISRA("-19.15")  

 
 
 
 
 
 
 

#pragma diag_pop

 

 


typedef char            xdc_Char;
typedef unsigned char   xdc_UChar;
typedef short           xdc_Short;
typedef unsigned short  xdc_UShort;
typedef int             xdc_Int;
typedef unsigned int    xdc_UInt;
typedef long            xdc_Long;
typedef unsigned long   xdc_ULong;
typedef float           xdc_Float;
typedef double          xdc_Double;
typedef long double     xdc_LDouble;
typedef size_t          xdc_SizeT;
typedef va_list         xdc_VaList;

 

typedef unsigned short  xdc_Bool;        
typedef void            *xdc_Ptr;        
typedef const void      *xdc_CPtr;       
typedef char            *xdc_String;     
typedef const char      *xdc_CString;    








 
  typedef int             (*xdc_Fxn)();    



 










 



 








 







 





 







 





 





 





 





 





 







 






 






 


 










 




 


 

 






 




 
typedef int_least8_t    xdc_Int8;
typedef uint_least8_t   xdc_UInt8;
typedef int_least16_t   xdc_Int16;
typedef uint_least16_t  xdc_UInt16;
typedef int_least32_t   xdc_Int32;
typedef uint_least32_t  xdc_UInt32;

    typedef int_least64_t   xdc_Int64;
    typedef uint_least64_t   xdc_UInt64;




 
    typedef uint8_t     xdc_Bits8;
    typedef uint16_t    xdc_Bits16;
    typedef uint32_t    xdc_Bits32;
    typedef uint64_t    xdc_Bits64;



 
typedef intptr_t        xdc_IArg;
typedef uintptr_t       xdc_UArg;



 




 



 





 





 





 











 

 

typedef long long               xdc_LLong;
typedef unsigned long long      xdc_ULLong;







 
static inline xdc_Ptr xdc_iargToPtr(xdc_IArg a) { return ((xdc_Ptr)a); }
static inline xdc_Ptr xdc_uargToPtr(xdc_UArg a) { return ((xdc_Ptr)a); }

static inline xdc_Fxn xdc_iargToFxn(xdc_IArg a) { return ((xdc_Fxn)a); }
static inline xdc_Fxn xdc_uargToFxn(xdc_UArg a) { return ((xdc_Fxn)a); }






 
typedef union {
    xdc_Float f;
    xdc_IArg  a;
} xdc_FloatData;

static inline xdc_IArg xdc_floatToArg(xdc_Float f)
{
     xdc_FloatData u;
     u.f = f;

     return (u.a);
}

static inline xdc_Float xdc_argToFloat(xdc_IArg a)
{
     xdc_FloatData u;
     u.a = a;

     return (u.f);
}

 

 




typedef xdc_Char        Char;
typedef xdc_UChar       UChar;
typedef xdc_Short       Short;
typedef xdc_UShort      UShort;
typedef xdc_Int         Int;
typedef xdc_UInt        UInt;
typedef xdc_Long        Long;
typedef xdc_ULong       ULong;
typedef xdc_LLong       LLong;
typedef xdc_ULLong      ULLong;
typedef xdc_Float       Float;
typedef xdc_Double      Double;
typedef xdc_LDouble     LDouble;
typedef xdc_SizeT       SizeT;
typedef xdc_VaList      VaList;

typedef xdc_IArg        IArg;
typedef xdc_UArg        UArg;
typedef xdc_Bool        Bool;
typedef xdc_Int8        Int8;
typedef xdc_Int16       Int16;
typedef xdc_Int32       Int32;
typedef xdc_Fxn         Fxn;
typedef xdc_Ptr         Ptr;
typedef xdc_String      String;
typedef xdc_CString     CString;

typedef xdc_UInt8       UInt8;
typedef xdc_UInt16      UInt16;
typedef xdc_UInt32      UInt32;

 

 

typedef xdc_UInt8       Uint8;
typedef xdc_UInt16      Uint16;
typedef xdc_UInt32      Uint32;
typedef xdc_UInt        Uns;




 
typedef xdc_Int64       Int64;
typedef xdc_UInt64      UInt64;




 
typedef xdc_Bits8       Bits8;

typedef xdc_Bits16      Bits16;

typedef xdc_Bits32      Bits32;

typedef xdc_Bits64      Bits64;


 






 



 














  














 



 













 



 





 




 



























 




 









 






 















 




 








 













 



 


 






 





 

typedef struct xdc_runtime_IModule_Fxns__ xdc_runtime_IModule_Fxns__;
typedef const xdc_runtime_IModule_Fxns__* xdc_runtime_IModule_Module;



 




 

typedef struct xdc_runtime_Core_ObjDesc xdc_runtime_Core_ObjDesc;



 




 

typedef struct xdc_runtime_Diags_DictElem xdc_runtime_Diags_DictElem;



 

typedef struct xdc_runtime_Error_Data xdc_runtime_Error_Data;
typedef struct xdc_runtime_Error_Block xdc_runtime_Error_Block;
typedef struct xdc_runtime_Error_Module_State xdc_runtime_Error_Module_State;



 




 

typedef struct xdc_runtime_IGateProvider_Fxns__ xdc_runtime_IGateProvider_Fxns__;
typedef const xdc_runtime_IGateProvider_Fxns__* xdc_runtime_IGateProvider_Module;
typedef struct xdc_runtime_IGateProvider_Params xdc_runtime_IGateProvider_Params;
typedef struct xdc_runtime_IGateProvider___Object { xdc_runtime_IGateProvider_Fxns__* __fxns; xdc_Bits32 __label; } *xdc_runtime_IGateProvider_Handle;



 

typedef struct xdc_runtime_GateNull_Fxns__ xdc_runtime_GateNull_Fxns__;
typedef const xdc_runtime_GateNull_Fxns__* xdc_runtime_GateNull_Module;
typedef struct xdc_runtime_GateNull_Params xdc_runtime_GateNull_Params;
typedef struct xdc_runtime_GateNull_Object xdc_runtime_GateNull_Object;
typedef struct xdc_runtime_GateNull_Struct xdc_runtime_GateNull_Struct;
typedef xdc_runtime_GateNull_Object* xdc_runtime_GateNull_Handle;
typedef struct xdc_runtime_GateNull_Object__ xdc_runtime_GateNull_Instance_State;
typedef xdc_runtime_GateNull_Object* xdc_runtime_GateNull_Instance;



 

typedef struct xdc_runtime_Log_EventRec xdc_runtime_Log_EventRec;



 

typedef struct xdc_runtime_ILogger_Fxns__ xdc_runtime_ILogger_Fxns__;
typedef const xdc_runtime_ILogger_Fxns__* xdc_runtime_ILogger_Module;
typedef struct xdc_runtime_ILogger_Params xdc_runtime_ILogger_Params;
typedef struct xdc_runtime_ILogger___Object { xdc_runtime_ILogger_Fxns__* __fxns; xdc_Bits32 __label; } *xdc_runtime_ILogger_Handle;



 

typedef struct xdc_runtime_IFilterLogger_Fxns__ xdc_runtime_IFilterLogger_Fxns__;
typedef const xdc_runtime_IFilterLogger_Fxns__* xdc_runtime_IFilterLogger_Module;
typedef struct xdc_runtime_IFilterLogger_Params xdc_runtime_IFilterLogger_Params;
typedef struct xdc_runtime_IFilterLogger___Object { xdc_runtime_IFilterLogger_Fxns__* __fxns; xdc_Bits32 __label; } *xdc_runtime_IFilterLogger_Handle;



 

typedef struct xdc_runtime_LoggerBuf_Entry xdc_runtime_LoggerBuf_Entry;
typedef struct xdc_runtime_LoggerBuf_Module_State xdc_runtime_LoggerBuf_Module_State;
typedef struct xdc_runtime_LoggerBuf_Fxns__ xdc_runtime_LoggerBuf_Fxns__;
typedef const xdc_runtime_LoggerBuf_Fxns__* xdc_runtime_LoggerBuf_Module;
typedef struct xdc_runtime_LoggerBuf_Params xdc_runtime_LoggerBuf_Params;
typedef struct xdc_runtime_LoggerBuf_Object xdc_runtime_LoggerBuf_Object;
typedef struct xdc_runtime_LoggerBuf_Struct xdc_runtime_LoggerBuf_Struct;
typedef xdc_runtime_LoggerBuf_Object* xdc_runtime_LoggerBuf_Handle;
typedef struct xdc_runtime_LoggerBuf_Object__ xdc_runtime_LoggerBuf_Instance_State;
typedef xdc_runtime_LoggerBuf_Object* xdc_runtime_LoggerBuf_Instance;



 

typedef struct xdc_runtime_LoggerCallback_Fxns__ xdc_runtime_LoggerCallback_Fxns__;
typedef const xdc_runtime_LoggerCallback_Fxns__* xdc_runtime_LoggerCallback_Module;
typedef struct xdc_runtime_LoggerCallback_Params xdc_runtime_LoggerCallback_Params;
typedef struct xdc_runtime_LoggerCallback_Object xdc_runtime_LoggerCallback_Object;
typedef struct xdc_runtime_LoggerCallback_Struct xdc_runtime_LoggerCallback_Struct;
typedef xdc_runtime_LoggerCallback_Object* xdc_runtime_LoggerCallback_Handle;
typedef struct xdc_runtime_LoggerCallback_Object__ xdc_runtime_LoggerCallback_Instance_State;
typedef xdc_runtime_LoggerCallback_Object* xdc_runtime_LoggerCallback_Instance;



 

typedef struct xdc_runtime_LoggerSys_Fxns__ xdc_runtime_LoggerSys_Fxns__;
typedef const xdc_runtime_LoggerSys_Fxns__* xdc_runtime_LoggerSys_Module;
typedef struct xdc_runtime_LoggerSys_Params xdc_runtime_LoggerSys_Params;
typedef struct xdc_runtime_LoggerSys_Object xdc_runtime_LoggerSys_Object;
typedef struct xdc_runtime_LoggerSys_Struct xdc_runtime_LoggerSys_Struct;
typedef xdc_runtime_LoggerSys_Object* xdc_runtime_LoggerSys_Handle;
typedef struct xdc_runtime_LoggerSys_Object__ xdc_runtime_LoggerSys_Instance_State;
typedef xdc_runtime_LoggerSys_Object* xdc_runtime_LoggerSys_Instance;



 




 

typedef struct xdc_runtime_Memory_Stats xdc_runtime_Memory_Stats;
typedef struct xdc_runtime_Memory_Module_State xdc_runtime_Memory_Module_State;



 

typedef struct xdc_runtime_IHeap_Fxns__ xdc_runtime_IHeap_Fxns__;
typedef const xdc_runtime_IHeap_Fxns__* xdc_runtime_IHeap_Module;
typedef struct xdc_runtime_IHeap_Params xdc_runtime_IHeap_Params;
typedef struct xdc_runtime_IHeap___Object { xdc_runtime_IHeap_Fxns__* __fxns; xdc_Bits32 __label; } *xdc_runtime_IHeap_Handle;



 

typedef struct xdc_runtime_HeapMin_Fxns__ xdc_runtime_HeapMin_Fxns__;
typedef const xdc_runtime_HeapMin_Fxns__* xdc_runtime_HeapMin_Module;
typedef struct xdc_runtime_HeapMin_Params xdc_runtime_HeapMin_Params;
typedef struct xdc_runtime_HeapMin_Object xdc_runtime_HeapMin_Object;
typedef struct xdc_runtime_HeapMin_Struct xdc_runtime_HeapMin_Struct;
typedef xdc_runtime_HeapMin_Object* xdc_runtime_HeapMin_Handle;
typedef struct xdc_runtime_HeapMin_Object__ xdc_runtime_HeapMin_Instance_State;
typedef xdc_runtime_HeapMin_Object* xdc_runtime_HeapMin_Instance;



 

typedef struct xdc_runtime_HeapStd_Module_State xdc_runtime_HeapStd_Module_State;
typedef struct xdc_runtime_HeapStd_Fxns__ xdc_runtime_HeapStd_Fxns__;
typedef const xdc_runtime_HeapStd_Fxns__* xdc_runtime_HeapStd_Module;
typedef struct xdc_runtime_HeapStd_Params xdc_runtime_HeapStd_Params;
typedef struct xdc_runtime_HeapStd_Object xdc_runtime_HeapStd_Object;
typedef struct xdc_runtime_HeapStd_Struct xdc_runtime_HeapStd_Struct;
typedef xdc_runtime_HeapStd_Object* xdc_runtime_HeapStd_Handle;
typedef struct xdc_runtime_HeapStd_Object__ xdc_runtime_HeapStd_Instance_State;
typedef xdc_runtime_HeapStd_Object* xdc_runtime_HeapStd_Instance;



 

typedef struct xdc_runtime_Registry_Module_State xdc_runtime_Registry_Module_State;



 

typedef struct xdc_runtime_Rta_CommandPacket xdc_runtime_Rta_CommandPacket;
typedef struct xdc_runtime_Rta_ResponsePacket xdc_runtime_Rta_ResponsePacket;



 

typedef struct xdc_runtime_Startup_IdMap xdc_runtime_Startup_IdMap;
typedef struct xdc_runtime_Startup_Module_State xdc_runtime_Startup_Module_State;



 

typedef struct xdc_runtime_System_ParseData xdc_runtime_System_ParseData;
typedef struct xdc_runtime_System_Module_State xdc_runtime_System_Module_State;



 

typedef struct xdc_runtime_ISystemSupport_Fxns__ xdc_runtime_ISystemSupport_Fxns__;
typedef const xdc_runtime_ISystemSupport_Fxns__* xdc_runtime_ISystemSupport_Module;



 

typedef struct xdc_runtime_SysCallback_Fxns__ xdc_runtime_SysCallback_Fxns__;
typedef const xdc_runtime_SysCallback_Fxns__* xdc_runtime_SysCallback_Module;



 

typedef struct xdc_runtime_SysMin_Module_State xdc_runtime_SysMin_Module_State;
typedef struct xdc_runtime_SysMin_Fxns__ xdc_runtime_SysMin_Fxns__;
typedef const xdc_runtime_SysMin_Fxns__* xdc_runtime_SysMin_Module;



 

typedef struct xdc_runtime_SysStd_Fxns__ xdc_runtime_SysStd_Fxns__;
typedef const xdc_runtime_SysStd_Fxns__* xdc_runtime_SysStd_Module;



 

typedef struct xdc_runtime_Text_Node xdc_runtime_Text_Node;
typedef struct xdc_runtime_Text_MatchVisState xdc_runtime_Text_MatchVisState;
typedef struct xdc_runtime_Text_PrintVisState xdc_runtime_Text_PrintVisState;
typedef struct xdc_runtime_Text_Module_State xdc_runtime_Text_Module_State;



 

typedef struct xdc_runtime_ITimestampClient_Fxns__ xdc_runtime_ITimestampClient_Fxns__;
typedef const xdc_runtime_ITimestampClient_Fxns__* xdc_runtime_ITimestampClient_Module;



 

typedef struct xdc_runtime_Timestamp_Fxns__ xdc_runtime_Timestamp_Fxns__;
typedef const xdc_runtime_Timestamp_Fxns__* xdc_runtime_Timestamp_Module;



 

typedef struct xdc_runtime_ITimestampProvider_Fxns__ xdc_runtime_ITimestampProvider_Fxns__;
typedef const xdc_runtime_ITimestampProvider_Fxns__* xdc_runtime_ITimestampProvider_Module;



 

typedef struct xdc_runtime_TimestampNull_Fxns__ xdc_runtime_TimestampNull_Fxns__;
typedef const xdc_runtime_TimestampNull_Fxns__* xdc_runtime_TimestampNull_Module;



 

typedef struct xdc_runtime_TimestampStd_Fxns__ xdc_runtime_TimestampStd_Fxns__;
typedef const xdc_runtime_TimestampStd_Fxns__* xdc_runtime_TimestampStd_Module;



 

typedef struct xdc_runtime_Types_CordAddr__ xdc_runtime_Types_CordAddr__;
typedef struct xdc_runtime_Types_GateRef__ xdc_runtime_Types_GateRef__;
typedef struct xdc_runtime_Types_Label xdc_runtime_Types_Label;
typedef struct xdc_runtime_Types_Site xdc_runtime_Types_Site;
typedef struct xdc_runtime_Types_Timestamp64 xdc_runtime_Types_Timestamp64;
typedef struct xdc_runtime_Types_FreqHz xdc_runtime_Types_FreqHz;
typedef struct xdc_runtime_Types_RegDesc xdc_runtime_Types_RegDesc;
typedef struct xdc_runtime_Types_Vec xdc_runtime_Types_Vec;
typedef struct xdc_runtime_Types_Link xdc_runtime_Types_Link;
typedef struct xdc_runtime_Types_InstHdr xdc_runtime_Types_InstHdr;
typedef struct xdc_runtime_Types_PrmsHdr xdc_runtime_Types_PrmsHdr;
typedef struct xdc_runtime_Types_Base xdc_runtime_Types_Base;
typedef struct xdc_runtime_Types_SysFxns xdc_runtime_Types_SysFxns;
typedef struct xdc_runtime_Types_SysFxns2 xdc_runtime_Types_SysFxns2;



 

typedef struct xdc_runtime_IInstance_Fxns__ xdc_runtime_IInstance_Fxns__;
typedef const xdc_runtime_IInstance_Fxns__* xdc_runtime_IInstance_Module;
typedef struct xdc_runtime_IInstance_Params xdc_runtime_IInstance_Params;
typedef struct xdc_runtime_IInstance___Object { xdc_runtime_IInstance_Fxns__* __fxns; xdc_Bits32 __label; } *xdc_runtime_IInstance_Handle;



 

typedef struct xdc_runtime_LoggerBuf_TimestampProxy_Fxns__ xdc_runtime_LoggerBuf_TimestampProxy_Fxns__;
typedef const xdc_runtime_LoggerBuf_TimestampProxy_Fxns__* xdc_runtime_LoggerBuf_TimestampProxy_Module;



 

typedef struct xdc_runtime_LoggerBuf_Module_GateProxy_Fxns__ xdc_runtime_LoggerBuf_Module_GateProxy_Fxns__;
typedef const xdc_runtime_LoggerBuf_Module_GateProxy_Fxns__* xdc_runtime_LoggerBuf_Module_GateProxy_Module;
typedef struct xdc_runtime_LoggerBuf_Module_GateProxy_Params xdc_runtime_LoggerBuf_Module_GateProxy_Params;
typedef struct xdc_runtime_IGateProvider___Object *xdc_runtime_LoggerBuf_Module_GateProxy_Handle;



 

typedef struct xdc_runtime_LoggerSys_TimestampProxy_Fxns__ xdc_runtime_LoggerSys_TimestampProxy_Fxns__;
typedef const xdc_runtime_LoggerSys_TimestampProxy_Fxns__* xdc_runtime_LoggerSys_TimestampProxy_Module;



 

typedef struct xdc_runtime_Main_Module_GateProxy_Fxns__ xdc_runtime_Main_Module_GateProxy_Fxns__;
typedef const xdc_runtime_Main_Module_GateProxy_Fxns__* xdc_runtime_Main_Module_GateProxy_Module;
typedef struct xdc_runtime_Main_Module_GateProxy_Params xdc_runtime_Main_Module_GateProxy_Params;
typedef struct xdc_runtime_IGateProvider___Object *xdc_runtime_Main_Module_GateProxy_Handle;



 

typedef struct xdc_runtime_Memory_HeapProxy_Fxns__ xdc_runtime_Memory_HeapProxy_Fxns__;
typedef const xdc_runtime_Memory_HeapProxy_Fxns__* xdc_runtime_Memory_HeapProxy_Module;
typedef struct xdc_runtime_Memory_HeapProxy_Params xdc_runtime_Memory_HeapProxy_Params;
typedef struct xdc_runtime_IHeap___Object *xdc_runtime_Memory_HeapProxy_Handle;



 

typedef struct xdc_runtime_System_SupportProxy_Fxns__ xdc_runtime_System_SupportProxy_Fxns__;
typedef const xdc_runtime_System_SupportProxy_Fxns__* xdc_runtime_System_SupportProxy_Module;



 

typedef struct xdc_runtime_System_Module_GateProxy_Fxns__ xdc_runtime_System_Module_GateProxy_Fxns__;
typedef const xdc_runtime_System_Module_GateProxy_Fxns__* xdc_runtime_System_Module_GateProxy_Module;
typedef struct xdc_runtime_System_Module_GateProxy_Params xdc_runtime_System_Module_GateProxy_Params;
typedef struct xdc_runtime_IGateProvider___Object *xdc_runtime_System_Module_GateProxy_Handle;



 

typedef struct xdc_runtime_Timestamp_SupportProxy_Fxns__ xdc_runtime_Timestamp_SupportProxy_Fxns__;
typedef const xdc_runtime_Timestamp_SupportProxy_Fxns__* xdc_runtime_Timestamp_SupportProxy_Module;






 

 
typedef xdc_Bits16 xdc_runtime_Types_ModuleId;

 
typedef xdc_Bits16 xdc_runtime_Types_DiagsMask;

 
typedef xdc_Bits32 xdc_runtime_Types_Event;

 
typedef xdc_runtime_Types_Event xdc_runtime_Types_EventId;

 
struct xdc_runtime_Types_CordAddr__;

 
typedef xdc_runtime_Types_CordAddr__ *xdc_runtime_Types_CordAddr;

 
struct xdc_runtime_Types_GateRef__;

 
typedef xdc_runtime_Types_GateRef__ *xdc_runtime_Types_GateRef;

 
typedef xdc_Bits16 xdc_runtime_Types_RopeId;

 
enum xdc_runtime_Types_CreatePolicy {
    xdc_runtime_Types_STATIC_POLICY,
    xdc_runtime_Types_CREATE_POLICY,
    xdc_runtime_Types_DELETE_POLICY
};
typedef enum xdc_runtime_Types_CreatePolicy xdc_runtime_Types_CreatePolicy;

 
enum xdc_runtime_Types_OutputPolicy {
    xdc_runtime_Types_COMMON_FILE,
    xdc_runtime_Types_SEPARATE_FILE,
    xdc_runtime_Types_NO_FILE
};
typedef enum xdc_runtime_Types_OutputPolicy xdc_runtime_Types_OutputPolicy;

 
struct xdc_runtime_Types_Label {
    xdc_Ptr handle;
    xdc_runtime_Types_ModuleId modId;
    xdc_String iname;
    xdc_Bool named;
};

 
struct xdc_runtime_Types_Site {
    xdc_runtime_Types_ModuleId mod;
    xdc_CString file;
    xdc_Int line;
};

 
struct xdc_runtime_Types_Timestamp64 {
    xdc_Bits32 hi;
    xdc_Bits32 lo;
};

 
struct xdc_runtime_Types_FreqHz {
    xdc_Bits32 hi;
    xdc_Bits32 lo;
};

 
struct xdc_runtime_Types_RegDesc {
    xdc_runtime_Types_RegDesc *next;
    xdc_CString modName;
    xdc_runtime_Types_ModuleId id;
    xdc_runtime_Types_DiagsMask mask;
};




 

 
typedef xdc_Bits32 xdc_runtime_Types_LogEvent;

 
typedef void (*xdc_runtime_Types_LoggerFxn0)(xdc_Ptr, xdc_runtime_Types_LogEvent, xdc_runtime_Types_ModuleId);

 
typedef void (*xdc_runtime_Types_LoggerFxn1)(xdc_Ptr, xdc_runtime_Types_LogEvent, xdc_runtime_Types_ModuleId, xdc_IArg);

 
typedef void (*xdc_runtime_Types_LoggerFxn2)(xdc_Ptr, xdc_runtime_Types_LogEvent, xdc_runtime_Types_ModuleId, xdc_IArg, xdc_IArg);

 
typedef void (*xdc_runtime_Types_LoggerFxn4)(xdc_Ptr, xdc_runtime_Types_LogEvent, xdc_runtime_Types_ModuleId, xdc_IArg, xdc_IArg, xdc_IArg, xdc_IArg);

 
typedef void (*xdc_runtime_Types_LoggerFxn8)(xdc_Ptr, xdc_runtime_Types_LogEvent, xdc_runtime_Types_ModuleId, xdc_IArg, xdc_IArg, xdc_IArg, xdc_IArg, xdc_IArg, xdc_IArg, xdc_IArg, xdc_IArg);

 
struct xdc_runtime_Types_Vec {
    xdc_Int len;
    xdc_Ptr arr;
};

 
struct xdc_runtime_Types_Link {
    xdc_runtime_Types_Link *next;
    xdc_runtime_Types_Link *prev;
};

 
struct xdc_runtime_Types_InstHdr {
    xdc_runtime_Types_Link link;
};

 
struct xdc_runtime_Types_PrmsHdr {
    xdc_SizeT size;
    xdc_Ptr self;
    xdc_Ptr modFxns;
    xdc_Ptr instPrms;
};

 
struct xdc_runtime_Types_Base {
    xdc_runtime_Types_Base *base;
};

 
struct xdc_runtime_Types_SysFxns {
    xdc_Ptr (*__create)(xdc_Ptr, xdc_SizeT, xdc_Ptr, const xdc_Ptr, xdc_SizeT, xdc_runtime_Error_Block*);
    void (*__delete)(xdc_Ptr);
    xdc_runtime_Types_Label *(*__label)(xdc_Ptr, xdc_runtime_Types_Label*);
    xdc_runtime_Types_ModuleId __mid;
};

 
struct xdc_runtime_Types_SysFxns2 {
    xdc_Ptr (*__create)(xdc_Ptr, xdc_SizeT, xdc_Ptr, const xdc_UChar*, xdc_SizeT, xdc_runtime_Error_Block*);
    void (*__delete)(xdc_Ptr);
    xdc_runtime_Types_Label *(*__label)(xdc_Ptr, xdc_runtime_Types_Label*);
    xdc_runtime_Types_ModuleId __mid;
};




 

 
typedef xdc_Bits32 CT__xdc_runtime_Types_Module__diagsEnabled;
extern  const CT__xdc_runtime_Types_Module__diagsEnabled xdc_runtime_Types_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__xdc_runtime_Types_Module__diagsIncluded;
extern  const CT__xdc_runtime_Types_Module__diagsIncluded xdc_runtime_Types_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__xdc_runtime_Types_Module__diagsMask;
extern  const CT__xdc_runtime_Types_Module__diagsMask xdc_runtime_Types_Module__diagsMask__C;

 
typedef xdc_Ptr CT__xdc_runtime_Types_Module__gateObj;
extern  const CT__xdc_runtime_Types_Module__gateObj xdc_runtime_Types_Module__gateObj__C;

 
typedef xdc_Ptr CT__xdc_runtime_Types_Module__gatePrms;
extern  const CT__xdc_runtime_Types_Module__gatePrms xdc_runtime_Types_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__xdc_runtime_Types_Module__id;
extern  const CT__xdc_runtime_Types_Module__id xdc_runtime_Types_Module__id__C;

 
typedef xdc_Bool CT__xdc_runtime_Types_Module__loggerDefined;
extern  const CT__xdc_runtime_Types_Module__loggerDefined xdc_runtime_Types_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__xdc_runtime_Types_Module__loggerObj;
extern  const CT__xdc_runtime_Types_Module__loggerObj xdc_runtime_Types_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__xdc_runtime_Types_Module__loggerFxn0;
extern  const CT__xdc_runtime_Types_Module__loggerFxn0 xdc_runtime_Types_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__xdc_runtime_Types_Module__loggerFxn1;
extern  const CT__xdc_runtime_Types_Module__loggerFxn1 xdc_runtime_Types_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__xdc_runtime_Types_Module__loggerFxn2;
extern  const CT__xdc_runtime_Types_Module__loggerFxn2 xdc_runtime_Types_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__xdc_runtime_Types_Module__loggerFxn4;
extern  const CT__xdc_runtime_Types_Module__loggerFxn4 xdc_runtime_Types_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__xdc_runtime_Types_Module__loggerFxn8;
extern  const CT__xdc_runtime_Types_Module__loggerFxn8 xdc_runtime_Types_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__xdc_runtime_Types_Module__startupDoneFxn)(void);
extern  const CT__xdc_runtime_Types_Module__startupDoneFxn xdc_runtime_Types_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__xdc_runtime_Types_Object__count;
extern  const CT__xdc_runtime_Types_Object__count xdc_runtime_Types_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__xdc_runtime_Types_Object__heap;
extern  const CT__xdc_runtime_Types_Object__heap xdc_runtime_Types_Object__heap__C;

 
typedef xdc_SizeT CT__xdc_runtime_Types_Object__sizeof;
extern  const CT__xdc_runtime_Types_Object__sizeof xdc_runtime_Types_Object__sizeof__C;

 
typedef xdc_Ptr CT__xdc_runtime_Types_Object__table;
extern  const CT__xdc_runtime_Types_Object__table xdc_runtime_Types_Object__table__C;




 

 

 

extern xdc_Bool xdc_runtime_Types_Module__startupDone__S( void );




 

 

 

 

 
static inline CT__xdc_runtime_Types_Module__id xdc_runtime_Types_Module_id( void ) 
{
    return xdc_runtime_Types_Module__id__C;
}

 
static inline xdc_Bool xdc_runtime_Types_Module_hasMask( void ) 
{
    return xdc_runtime_Types_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 xdc_runtime_Types_Module_getMask( void ) 
{
    return xdc_runtime_Types_Module__diagsMask__C != 0 ? *xdc_runtime_Types_Module__diagsMask__C : 0;
}

 
static inline void xdc_runtime_Types_Module_setMask( xdc_Bits16 mask ) 
{
    if (xdc_runtime_Types_Module__diagsMask__C != 0) *xdc_runtime_Types_Module__diagsMask__C = mask;
}




 












 



 



 



 



 


 







 




 



 































 






 





 

typedef struct ti_sysbios_BIOS_intSize ti_sysbios_BIOS_intSize;
typedef struct ti_sysbios_BIOS_Module_State ti_sysbios_BIOS_Module_State;



 

typedef struct ti_sysbios_BIOS_RtsGateProxy_Fxns__ ti_sysbios_BIOS_RtsGateProxy_Fxns__;
typedef const ti_sysbios_BIOS_RtsGateProxy_Fxns__* ti_sysbios_BIOS_RtsGateProxy_Module;
typedef struct ti_sysbios_BIOS_RtsGateProxy_Params ti_sysbios_BIOS_RtsGateProxy_Params;
typedef struct xdc_runtime_IGateProvider___Object *ti_sysbios_BIOS_RtsGateProxy_Handle;








 















 




 








 







 















 




 





 




 



 












 





 















 




 








 







 















 




 





 




 



 







 













 




 








 







 















 




 





 




 



 







 















 




 





 




 



 






 














 




 








 







 















 




 





 




 



 






 














 




 








 







 















 




 





 




 



 





 




 

 
struct xdc_runtime_IInstance_Params {
    size_t __size;
    xdc_String name;
};




 

 
struct xdc_runtime_IInstance_Fxns__ {
    xdc_runtime_Types_Base* __base;
    const xdc_runtime_Types_SysFxns2 *__sysp;
    xdc_runtime_Types_SysFxns2 __sfxns;
};

 
extern const xdc_runtime_Types_Base xdc_runtime_IInstance_Interface__BASE__C;




 

 

extern xdc_runtime_IInstance_Handle xdc_runtime_IInstance_create(xdc_runtime_IInstance_Module, const xdc_runtime_IInstance_Params *, xdc_runtime_Error_Block *__eb);

 

extern void xdc_runtime_IInstance_delete(xdc_runtime_IInstance_Handle *);

 
static inline xdc_runtime_IInstance_Module xdc_runtime_IInstance_Handle_to_Module( xdc_runtime_IInstance_Handle inst )
{
    return inst->__fxns;
}

 
static inline xdc_runtime_Types_Label *xdc_runtime_IInstance_Handle_label( xdc_runtime_IInstance_Handle inst, xdc_runtime_Types_Label *lab )
{
    return inst->__fxns->__sysp->__label(inst, lab);
}

 
static inline xdc_runtime_Types_ModuleId xdc_runtime_IInstance_Module_id( xdc_runtime_IInstance_Module mod )
{
    return mod->__sysp->__mid;
}




 




 






 



 







 













 




 





 



 






 















 




 





 





 



 






 















 




 








 







 















 




 





 




 



 







 













 




 





 



 






 














 




 





 



 






 















 




 





 





 



 






 
















 




 








 







 















 




 





 




 



 






 














 




 





 



 







 














 




 





 



 





 




 

 
typedef xdc_Bits32 CT__xdc_runtime_Memory_HeapProxy_Module__diagsEnabled;
extern  const CT__xdc_runtime_Memory_HeapProxy_Module__diagsEnabled xdc_runtime_Memory_HeapProxy_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__xdc_runtime_Memory_HeapProxy_Module__diagsIncluded;
extern  const CT__xdc_runtime_Memory_HeapProxy_Module__diagsIncluded xdc_runtime_Memory_HeapProxy_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__xdc_runtime_Memory_HeapProxy_Module__diagsMask;
extern  const CT__xdc_runtime_Memory_HeapProxy_Module__diagsMask xdc_runtime_Memory_HeapProxy_Module__diagsMask__C;

 
typedef xdc_Ptr CT__xdc_runtime_Memory_HeapProxy_Module__gateObj;
extern  const CT__xdc_runtime_Memory_HeapProxy_Module__gateObj xdc_runtime_Memory_HeapProxy_Module__gateObj__C;

 
typedef xdc_Ptr CT__xdc_runtime_Memory_HeapProxy_Module__gatePrms;
extern  const CT__xdc_runtime_Memory_HeapProxy_Module__gatePrms xdc_runtime_Memory_HeapProxy_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__xdc_runtime_Memory_HeapProxy_Module__id;
extern  const CT__xdc_runtime_Memory_HeapProxy_Module__id xdc_runtime_Memory_HeapProxy_Module__id__C;

 
typedef xdc_Bool CT__xdc_runtime_Memory_HeapProxy_Module__loggerDefined;
extern  const CT__xdc_runtime_Memory_HeapProxy_Module__loggerDefined xdc_runtime_Memory_HeapProxy_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__xdc_runtime_Memory_HeapProxy_Module__loggerObj;
extern  const CT__xdc_runtime_Memory_HeapProxy_Module__loggerObj xdc_runtime_Memory_HeapProxy_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__xdc_runtime_Memory_HeapProxy_Module__loggerFxn0;
extern  const CT__xdc_runtime_Memory_HeapProxy_Module__loggerFxn0 xdc_runtime_Memory_HeapProxy_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__xdc_runtime_Memory_HeapProxy_Module__loggerFxn1;
extern  const CT__xdc_runtime_Memory_HeapProxy_Module__loggerFxn1 xdc_runtime_Memory_HeapProxy_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__xdc_runtime_Memory_HeapProxy_Module__loggerFxn2;
extern  const CT__xdc_runtime_Memory_HeapProxy_Module__loggerFxn2 xdc_runtime_Memory_HeapProxy_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__xdc_runtime_Memory_HeapProxy_Module__loggerFxn4;
extern  const CT__xdc_runtime_Memory_HeapProxy_Module__loggerFxn4 xdc_runtime_Memory_HeapProxy_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__xdc_runtime_Memory_HeapProxy_Module__loggerFxn8;
extern  const CT__xdc_runtime_Memory_HeapProxy_Module__loggerFxn8 xdc_runtime_Memory_HeapProxy_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__xdc_runtime_Memory_HeapProxy_Module__startupDoneFxn)(void);
extern  const CT__xdc_runtime_Memory_HeapProxy_Module__startupDoneFxn xdc_runtime_Memory_HeapProxy_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__xdc_runtime_Memory_HeapProxy_Object__count;
extern  const CT__xdc_runtime_Memory_HeapProxy_Object__count xdc_runtime_Memory_HeapProxy_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__xdc_runtime_Memory_HeapProxy_Object__heap;
extern  const CT__xdc_runtime_Memory_HeapProxy_Object__heap xdc_runtime_Memory_HeapProxy_Object__heap__C;

 
typedef xdc_SizeT CT__xdc_runtime_Memory_HeapProxy_Object__sizeof;
extern  const CT__xdc_runtime_Memory_HeapProxy_Object__sizeof xdc_runtime_Memory_HeapProxy_Object__sizeof__C;

 
typedef xdc_Ptr CT__xdc_runtime_Memory_HeapProxy_Object__table;
extern  const CT__xdc_runtime_Memory_HeapProxy_Object__table xdc_runtime_Memory_HeapProxy_Object__table__C;




 

 
struct xdc_runtime_Memory_HeapProxy_Params {
    size_t __size;
    const void *__self;
    void *__fxns;
    xdc_runtime_IInstance_Params *instance;
    xdc_runtime_IInstance_Params __iprms;
};

 
struct xdc_runtime_Memory_HeapProxy_Struct {
    const xdc_runtime_Memory_HeapProxy_Fxns__ *__fxns;
    xdc_runtime_Types_CordAddr __name;
};




 

 
struct xdc_runtime_Memory_HeapProxy_Fxns__ {
    xdc_runtime_Types_Base* __base;
    const xdc_runtime_Types_SysFxns2 *__sysp;
    xdc_Ptr (*alloc)(xdc_runtime_Memory_HeapProxy_Handle, xdc_SizeT, xdc_SizeT, xdc_runtime_Error_Block*);
    void (*free)(xdc_runtime_Memory_HeapProxy_Handle, xdc_Ptr, xdc_SizeT);
    xdc_Bool (*isBlocking)(xdc_runtime_Memory_HeapProxy_Handle);
    void (*getStats)(xdc_runtime_Memory_HeapProxy_Handle, xdc_runtime_Memory_Stats*);
    xdc_runtime_Types_SysFxns2 __sfxns;
};

 
extern const xdc_runtime_Memory_HeapProxy_Fxns__ xdc_runtime_Memory_HeapProxy_Module__FXNS__C;




 

 

 

extern xdc_runtime_Types_Label *xdc_runtime_Memory_HeapProxy_Handle__label__S( xdc_Ptr obj, xdc_runtime_Types_Label *lab );

 

extern xdc_Bool xdc_runtime_Memory_HeapProxy_Module__startupDone__S( void );

 

extern xdc_Ptr xdc_runtime_Memory_HeapProxy_Object__create__S( xdc_Ptr __oa, xdc_SizeT __osz, xdc_Ptr __aa, const xdc_UChar *__pa, xdc_SizeT __psz, xdc_runtime_Error_Block *__eb );

 

extern xdc_runtime_Memory_HeapProxy_Handle xdc_runtime_Memory_HeapProxy_create( const xdc_runtime_Memory_HeapProxy_Params *__prms, xdc_runtime_Error_Block *__eb );

 

extern void xdc_runtime_Memory_HeapProxy_Object__delete__S( xdc_Ptr instp );

 

extern void xdc_runtime_Memory_HeapProxy_delete(xdc_runtime_Memory_HeapProxy_Handle *instp);

 

extern void xdc_runtime_Memory_HeapProxy_Object__destruct__S( xdc_Ptr objp );

 

extern xdc_Ptr xdc_runtime_Memory_HeapProxy_Object__get__S( xdc_Ptr oarr, xdc_Int i );

 

extern xdc_Ptr xdc_runtime_Memory_HeapProxy_Object__first__S( void );

 

extern xdc_Ptr xdc_runtime_Memory_HeapProxy_Object__next__S( xdc_Ptr obj );

 

extern void xdc_runtime_Memory_HeapProxy_Params__init__S( xdc_Ptr dst, const void *src, xdc_SizeT psz, xdc_SizeT isz );

 

extern xdc_Bool xdc_runtime_Memory_HeapProxy_Proxy__abstract__S( void );

 

extern xdc_Ptr xdc_runtime_Memory_HeapProxy_Proxy__delegate__S( void );

 

extern xdc_Ptr xdc_runtime_Memory_HeapProxy_alloc__E( xdc_runtime_Memory_HeapProxy_Handle __inst, xdc_SizeT size, xdc_SizeT align, xdc_runtime_Error_Block *eb );

 

extern void xdc_runtime_Memory_HeapProxy_free__E( xdc_runtime_Memory_HeapProxy_Handle __inst, xdc_Ptr block, xdc_SizeT size );

 

extern xdc_Bool xdc_runtime_Memory_HeapProxy_isBlocking__E( xdc_runtime_Memory_HeapProxy_Handle __inst );

 

extern void xdc_runtime_Memory_HeapProxy_getStats__E( xdc_runtime_Memory_HeapProxy_Handle __inst, xdc_runtime_Memory_Stats *stats );




 

 
static inline xdc_runtime_IHeap_Module xdc_runtime_Memory_HeapProxy_Module_upCast( void )
{
    return (xdc_runtime_IHeap_Module)xdc_runtime_Memory_HeapProxy_Proxy__delegate__S();
}

 

 
static inline xdc_runtime_IHeap_Handle xdc_runtime_Memory_HeapProxy_Handle_upCast( xdc_runtime_Memory_HeapProxy_Handle i )
{
    return (xdc_runtime_IHeap_Handle)i;
}

 

 
static inline xdc_runtime_Memory_HeapProxy_Handle xdc_runtime_Memory_HeapProxy_Handle_downCast( xdc_runtime_IHeap_Handle i )
{
    xdc_runtime_IHeap_Handle i2 = (xdc_runtime_IHeap_Handle)i;
if (xdc_runtime_Memory_HeapProxy_Proxy__abstract__S()) return (xdc_runtime_Memory_HeapProxy_Handle)i;
    return (void*)i2->__fxns == (void*)xdc_runtime_Memory_HeapProxy_Proxy__delegate__S() ? (xdc_runtime_Memory_HeapProxy_Handle)i : 0;
}

 




 

 

 

 

 
static inline CT__xdc_runtime_Memory_HeapProxy_Module__id xdc_runtime_Memory_HeapProxy_Module_id( void ) 
{
    return xdc_runtime_Memory_HeapProxy_Module__id__C;
}

 

 

 
static inline void xdc_runtime_Memory_HeapProxy_Params_init( xdc_runtime_Memory_HeapProxy_Params *prms ) 
{
    if (prms) {
        xdc_runtime_Memory_HeapProxy_Params__init__S(prms, 0, sizeof(xdc_runtime_Memory_HeapProxy_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 
static inline void xdc_runtime_Memory_HeapProxy_Params_copy(xdc_runtime_Memory_HeapProxy_Params *dst, const xdc_runtime_Memory_HeapProxy_Params *src) 
{
    if (dst) {
        xdc_runtime_Memory_HeapProxy_Params__init__S(dst, (const void *)src, sizeof(xdc_runtime_Memory_HeapProxy_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}




 






 





 

 

 
typedef xdc_UArg xdc_runtime_Memory_Size;

 
struct xdc_runtime_Memory_Stats {
    xdc_runtime_Memory_Size totalSize;
    xdc_runtime_Memory_Size totalFreeSize;
    xdc_runtime_Memory_Size largestFreeSize;
};




 




 

 
typedef xdc_Bits32 CT__xdc_runtime_Memory_Module__diagsEnabled;
extern  const CT__xdc_runtime_Memory_Module__diagsEnabled xdc_runtime_Memory_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__xdc_runtime_Memory_Module__diagsIncluded;
extern  const CT__xdc_runtime_Memory_Module__diagsIncluded xdc_runtime_Memory_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__xdc_runtime_Memory_Module__diagsMask;
extern  const CT__xdc_runtime_Memory_Module__diagsMask xdc_runtime_Memory_Module__diagsMask__C;

 
typedef xdc_Ptr CT__xdc_runtime_Memory_Module__gateObj;
extern  const CT__xdc_runtime_Memory_Module__gateObj xdc_runtime_Memory_Module__gateObj__C;

 
typedef xdc_Ptr CT__xdc_runtime_Memory_Module__gatePrms;
extern  const CT__xdc_runtime_Memory_Module__gatePrms xdc_runtime_Memory_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__xdc_runtime_Memory_Module__id;
extern  const CT__xdc_runtime_Memory_Module__id xdc_runtime_Memory_Module__id__C;

 
typedef xdc_Bool CT__xdc_runtime_Memory_Module__loggerDefined;
extern  const CT__xdc_runtime_Memory_Module__loggerDefined xdc_runtime_Memory_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__xdc_runtime_Memory_Module__loggerObj;
extern  const CT__xdc_runtime_Memory_Module__loggerObj xdc_runtime_Memory_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__xdc_runtime_Memory_Module__loggerFxn0;
extern  const CT__xdc_runtime_Memory_Module__loggerFxn0 xdc_runtime_Memory_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__xdc_runtime_Memory_Module__loggerFxn1;
extern  const CT__xdc_runtime_Memory_Module__loggerFxn1 xdc_runtime_Memory_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__xdc_runtime_Memory_Module__loggerFxn2;
extern  const CT__xdc_runtime_Memory_Module__loggerFxn2 xdc_runtime_Memory_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__xdc_runtime_Memory_Module__loggerFxn4;
extern  const CT__xdc_runtime_Memory_Module__loggerFxn4 xdc_runtime_Memory_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__xdc_runtime_Memory_Module__loggerFxn8;
extern  const CT__xdc_runtime_Memory_Module__loggerFxn8 xdc_runtime_Memory_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__xdc_runtime_Memory_Module__startupDoneFxn)(void);
extern  const CT__xdc_runtime_Memory_Module__startupDoneFxn xdc_runtime_Memory_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__xdc_runtime_Memory_Object__count;
extern  const CT__xdc_runtime_Memory_Object__count xdc_runtime_Memory_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__xdc_runtime_Memory_Object__heap;
extern  const CT__xdc_runtime_Memory_Object__heap xdc_runtime_Memory_Object__heap__C;

 
typedef xdc_SizeT CT__xdc_runtime_Memory_Object__sizeof;
extern  const CT__xdc_runtime_Memory_Object__sizeof xdc_runtime_Memory_Object__sizeof__C;

 
typedef xdc_Ptr CT__xdc_runtime_Memory_Object__table;
extern  const CT__xdc_runtime_Memory_Object__table xdc_runtime_Memory_Object__table__C;

 
typedef xdc_runtime_IHeap_Handle CT__xdc_runtime_Memory_defaultHeapInstance;
extern  const CT__xdc_runtime_Memory_defaultHeapInstance xdc_runtime_Memory_defaultHeapInstance__C;




 

 

 

extern xdc_Bool xdc_runtime_Memory_Module__startupDone__S( void );

 

extern xdc_Ptr xdc_runtime_Memory_alloc__E( xdc_runtime_IHeap_Handle heap, xdc_SizeT size, xdc_SizeT align, xdc_runtime_Error_Block *eb );

 

extern xdc_Ptr xdc_runtime_Memory_calloc__E( xdc_runtime_IHeap_Handle heap, xdc_SizeT size, xdc_SizeT align, xdc_runtime_Error_Block *eb );

 

extern void xdc_runtime_Memory_free__E( xdc_runtime_IHeap_Handle heap, xdc_Ptr block, xdc_SizeT size );

 

extern void xdc_runtime_Memory_getStats__E( xdc_runtime_IHeap_Handle heap, xdc_runtime_Memory_Stats *stats );

 

extern xdc_Bool xdc_runtime_Memory_query__E( xdc_runtime_IHeap_Handle heap, xdc_Int qual );

 

extern xdc_SizeT xdc_runtime_Memory_getMaxDefaultTypeAlign__E( void );

 

extern xdc_Ptr xdc_runtime_Memory_valloc__E( xdc_runtime_IHeap_Handle heap, xdc_SizeT size, xdc_SizeT align, xdc_Char value, xdc_runtime_Error_Block *eb );




 

 

 

 

 
static inline CT__xdc_runtime_Memory_Module__id xdc_runtime_Memory_Module_id( void ) 
{
    return xdc_runtime_Memory_Module__id__C;
}

 
static inline xdc_Bool xdc_runtime_Memory_Module_hasMask( void ) 
{
    return xdc_runtime_Memory_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 xdc_runtime_Memory_Module_getMask( void ) 
{
    return xdc_runtime_Memory_Module__diagsMask__C != 0 ? *xdc_runtime_Memory_Module__diagsMask__C : 0;
}

 
static inline void xdc_runtime_Memory_Module_setMask( xdc_Bits16 mask ) 
{
    if (xdc_runtime_Memory_Module__diagsMask__C != 0) *xdc_runtime_Memory_Module__diagsMask__C = mask;
}




 






 





 



 





 




 

 
struct xdc_runtime_IHeap_Params {
    size_t __size;
    const void *__self;
    void *__fxns;
    xdc_runtime_IInstance_Params *instance;
};




 

 
struct xdc_runtime_IHeap_Fxns__ {
    xdc_runtime_Types_Base* __base;
    const xdc_runtime_Types_SysFxns2 *__sysp;
    xdc_Ptr (*alloc)(void*, xdc_SizeT, xdc_SizeT, xdc_runtime_Error_Block*);
    void (*free)(void*, xdc_Ptr, xdc_SizeT);
    xdc_Bool (*isBlocking)(void*);
    void (*getStats)(void*, xdc_runtime_Memory_Stats*);
    xdc_runtime_Types_SysFxns2 __sfxns;
};

 
extern const xdc_runtime_Types_Base xdc_runtime_IHeap_Interface__BASE__C;




 

 

extern xdc_runtime_IHeap_Handle xdc_runtime_IHeap_create(xdc_runtime_IHeap_Module, const xdc_runtime_IHeap_Params *, xdc_runtime_Error_Block *__eb);

 

extern void xdc_runtime_IHeap_delete(xdc_runtime_IHeap_Handle *);

 
static inline xdc_runtime_IHeap_Module xdc_runtime_IHeap_Handle_to_Module( xdc_runtime_IHeap_Handle inst )
{
    return inst->__fxns;
}

 
static inline xdc_runtime_Types_Label *xdc_runtime_IHeap_Handle_label( xdc_runtime_IHeap_Handle inst, xdc_runtime_Types_Label *lab )
{
    return inst->__fxns->__sysp->__label(inst, lab);
}

 
static inline xdc_runtime_Types_ModuleId xdc_runtime_IHeap_Module_id( xdc_runtime_IHeap_Module mod )
{
    return mod->__sysp->__mid;
}

 
static inline xdc_Ptr xdc_runtime_IHeap_alloc( xdc_runtime_IHeap_Handle __inst, xdc_SizeT size, xdc_SizeT align, xdc_runtime_Error_Block *eb )
{
    return __inst->__fxns->alloc((void*)__inst, size, align, eb);
}

 
static inline void xdc_runtime_IHeap_free( xdc_runtime_IHeap_Handle __inst, xdc_Ptr block, xdc_SizeT size )
{
    __inst->__fxns->free((void*)__inst, block, size);
}

 
static inline xdc_Bool xdc_runtime_IHeap_isBlocking( xdc_runtime_IHeap_Handle __inst )
{
    return __inst->__fxns->isBlocking((void*)__inst);
}

 
static inline void xdc_runtime_IHeap_getStats( xdc_runtime_IHeap_Handle __inst, xdc_runtime_Memory_Stats *stats )
{
    __inst->__fxns->getStats((void*)__inst, stats);
}




 






 

 
typedef xdc_Ptr (*xdc_runtime_IHeap_alloc_FxnT)(void *, xdc_SizeT, xdc_SizeT, xdc_runtime_Error_Block*);
static inline xdc_runtime_IHeap_alloc_FxnT xdc_runtime_IHeap_alloc_fxnP( xdc_runtime_IHeap_Handle __inst )
{
    return (xdc_runtime_IHeap_alloc_FxnT)__inst->__fxns->alloc;
}

 
typedef void (*xdc_runtime_IHeap_free_FxnT)(void *, xdc_Ptr, xdc_SizeT);
static inline xdc_runtime_IHeap_free_FxnT xdc_runtime_IHeap_free_fxnP( xdc_runtime_IHeap_Handle __inst )
{
    return (xdc_runtime_IHeap_free_FxnT)__inst->__fxns->free;
}

 
typedef xdc_Bool (*xdc_runtime_IHeap_isBlocking_FxnT)(void *);
static inline xdc_runtime_IHeap_isBlocking_FxnT xdc_runtime_IHeap_isBlocking_fxnP( xdc_runtime_IHeap_Handle __inst )
{
    return (xdc_runtime_IHeap_isBlocking_FxnT)__inst->__fxns->isBlocking;
}

 
typedef void (*xdc_runtime_IHeap_getStats_FxnT)(void *, xdc_runtime_Memory_Stats*);
static inline xdc_runtime_IHeap_getStats_FxnT xdc_runtime_IHeap_getStats_fxnP( xdc_runtime_IHeap_Handle __inst )
{
    return (xdc_runtime_IHeap_getStats_FxnT)__inst->__fxns->getStats;
}




 






 



 






 















 




 





 





 



 





 




 

 
struct xdc_runtime_IModule_Fxns__ {
    xdc_runtime_Types_Base* __base;
    const xdc_runtime_Types_SysFxns2 *__sysp;
    xdc_runtime_Types_SysFxns2 __sfxns;
};

 
extern const xdc_runtime_Types_Base xdc_runtime_IModule_Interface__BASE__C;




 

 
static inline xdc_runtime_Types_ModuleId xdc_runtime_IModule_Module_id( xdc_runtime_IModule_Module mod )
{
    return mod->__sysp->__mid;
}




 




 






 



 






 














 




 








 







 















 




 





 




 



 






 














 




 





 



 







 













 




 





 



 





 

 

 




 

 
struct xdc_runtime_IGateProvider_Params {
    size_t __size;
    const void *__self;
    void *__fxns;
    xdc_runtime_IInstance_Params *instance;
};




 

 
struct xdc_runtime_IGateProvider_Fxns__ {
    xdc_runtime_Types_Base* __base;
    const xdc_runtime_Types_SysFxns2 *__sysp;
    xdc_Bool (*query)(xdc_Int);
    xdc_IArg (*enter)(void*);
    void (*leave)(void*, xdc_IArg);
    xdc_runtime_Types_SysFxns2 __sfxns;
};

 
extern const xdc_runtime_Types_Base xdc_runtime_IGateProvider_Interface__BASE__C;




 

 

extern xdc_runtime_IGateProvider_Handle xdc_runtime_IGateProvider_create(xdc_runtime_IGateProvider_Module, const xdc_runtime_IGateProvider_Params *, xdc_runtime_Error_Block *__eb);

 

extern void xdc_runtime_IGateProvider_delete(xdc_runtime_IGateProvider_Handle *);

 
static inline xdc_runtime_IGateProvider_Module xdc_runtime_IGateProvider_Handle_to_Module( xdc_runtime_IGateProvider_Handle inst )
{
    return inst->__fxns;
}

 
static inline xdc_runtime_Types_Label *xdc_runtime_IGateProvider_Handle_label( xdc_runtime_IGateProvider_Handle inst, xdc_runtime_Types_Label *lab )
{
    return inst->__fxns->__sysp->__label(inst, lab);
}

 
static inline xdc_runtime_Types_ModuleId xdc_runtime_IGateProvider_Module_id( xdc_runtime_IGateProvider_Module mod )
{
    return mod->__sysp->__mid;
}

 
static inline xdc_Bool xdc_runtime_IGateProvider_query( xdc_runtime_IGateProvider_Module __inst, xdc_Int qual )
{
    return __inst->query(qual);
}

 
static inline xdc_IArg xdc_runtime_IGateProvider_enter( xdc_runtime_IGateProvider_Handle __inst )
{
    return __inst->__fxns->enter((void*)__inst);
}

 
static inline void xdc_runtime_IGateProvider_leave( xdc_runtime_IGateProvider_Handle __inst, xdc_IArg key )
{
    __inst->__fxns->leave((void*)__inst, key);
}




 






 

 
typedef xdc_Bool (*xdc_runtime_IGateProvider_query_FxnT)(xdc_Int);
static inline xdc_runtime_IGateProvider_query_FxnT xdc_runtime_IGateProvider_query_fxnP( xdc_runtime_IGateProvider_Module __inst )
{
    return (xdc_runtime_IGateProvider_query_FxnT)__inst->query;
}

 
typedef xdc_IArg (*xdc_runtime_IGateProvider_enter_FxnT)(void *);
static inline xdc_runtime_IGateProvider_enter_FxnT xdc_runtime_IGateProvider_enter_fxnP( xdc_runtime_IGateProvider_Handle __inst )
{
    return (xdc_runtime_IGateProvider_enter_FxnT)__inst->__fxns->enter;
}

 
typedef void (*xdc_runtime_IGateProvider_leave_FxnT)(void *, xdc_IArg);
static inline xdc_runtime_IGateProvider_leave_FxnT xdc_runtime_IGateProvider_leave_fxnP( xdc_runtime_IGateProvider_Handle __inst )
{
    return (xdc_runtime_IGateProvider_leave_FxnT)__inst->__fxns->leave;
}




 






 



 






 
















 




 








 







 















 




 





 




 



 






 














 




 





 



 







 














 




 





 



 





 

 

 




 

 
typedef xdc_Bits32 CT__xdc_runtime_Main_Module_GateProxy_Module__diagsEnabled;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Module__diagsEnabled xdc_runtime_Main_Module_GateProxy_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__xdc_runtime_Main_Module_GateProxy_Module__diagsIncluded;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Module__diagsIncluded xdc_runtime_Main_Module_GateProxy_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__xdc_runtime_Main_Module_GateProxy_Module__diagsMask;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Module__diagsMask xdc_runtime_Main_Module_GateProxy_Module__diagsMask__C;

 
typedef xdc_Ptr CT__xdc_runtime_Main_Module_GateProxy_Module__gateObj;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Module__gateObj xdc_runtime_Main_Module_GateProxy_Module__gateObj__C;

 
typedef xdc_Ptr CT__xdc_runtime_Main_Module_GateProxy_Module__gatePrms;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Module__gatePrms xdc_runtime_Main_Module_GateProxy_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__xdc_runtime_Main_Module_GateProxy_Module__id;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Module__id xdc_runtime_Main_Module_GateProxy_Module__id__C;

 
typedef xdc_Bool CT__xdc_runtime_Main_Module_GateProxy_Module__loggerDefined;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Module__loggerDefined xdc_runtime_Main_Module_GateProxy_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__xdc_runtime_Main_Module_GateProxy_Module__loggerObj;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Module__loggerObj xdc_runtime_Main_Module_GateProxy_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__xdc_runtime_Main_Module_GateProxy_Module__loggerFxn0;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Module__loggerFxn0 xdc_runtime_Main_Module_GateProxy_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__xdc_runtime_Main_Module_GateProxy_Module__loggerFxn1;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Module__loggerFxn1 xdc_runtime_Main_Module_GateProxy_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__xdc_runtime_Main_Module_GateProxy_Module__loggerFxn2;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Module__loggerFxn2 xdc_runtime_Main_Module_GateProxy_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__xdc_runtime_Main_Module_GateProxy_Module__loggerFxn4;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Module__loggerFxn4 xdc_runtime_Main_Module_GateProxy_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__xdc_runtime_Main_Module_GateProxy_Module__loggerFxn8;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Module__loggerFxn8 xdc_runtime_Main_Module_GateProxy_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__xdc_runtime_Main_Module_GateProxy_Module__startupDoneFxn)(void);
extern  const CT__xdc_runtime_Main_Module_GateProxy_Module__startupDoneFxn xdc_runtime_Main_Module_GateProxy_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__xdc_runtime_Main_Module_GateProxy_Object__count;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Object__count xdc_runtime_Main_Module_GateProxy_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__xdc_runtime_Main_Module_GateProxy_Object__heap;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Object__heap xdc_runtime_Main_Module_GateProxy_Object__heap__C;

 
typedef xdc_SizeT CT__xdc_runtime_Main_Module_GateProxy_Object__sizeof;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Object__sizeof xdc_runtime_Main_Module_GateProxy_Object__sizeof__C;

 
typedef xdc_Ptr CT__xdc_runtime_Main_Module_GateProxy_Object__table;
extern  const CT__xdc_runtime_Main_Module_GateProxy_Object__table xdc_runtime_Main_Module_GateProxy_Object__table__C;




 

 
struct xdc_runtime_Main_Module_GateProxy_Params {
    size_t __size;
    const void *__self;
    void *__fxns;
    xdc_runtime_IInstance_Params *instance;
    xdc_runtime_IInstance_Params __iprms;
};

 
struct xdc_runtime_Main_Module_GateProxy_Struct {
    const xdc_runtime_Main_Module_GateProxy_Fxns__ *__fxns;
    xdc_runtime_Types_CordAddr __name;
};




 

 
struct xdc_runtime_Main_Module_GateProxy_Fxns__ {
    xdc_runtime_Types_Base* __base;
    const xdc_runtime_Types_SysFxns2 *__sysp;
    xdc_Bool (*query)(xdc_Int);
    xdc_IArg (*enter)(xdc_runtime_Main_Module_GateProxy_Handle);
    void (*leave)(xdc_runtime_Main_Module_GateProxy_Handle, xdc_IArg);
    xdc_runtime_Types_SysFxns2 __sfxns;
};

 
extern const xdc_runtime_Main_Module_GateProxy_Fxns__ xdc_runtime_Main_Module_GateProxy_Module__FXNS__C;




 

 

 

extern xdc_runtime_Types_Label *xdc_runtime_Main_Module_GateProxy_Handle__label__S( xdc_Ptr obj, xdc_runtime_Types_Label *lab );

 

extern xdc_Bool xdc_runtime_Main_Module_GateProxy_Module__startupDone__S( void );

 

extern xdc_Ptr xdc_runtime_Main_Module_GateProxy_Object__create__S( xdc_Ptr __oa, xdc_SizeT __osz, xdc_Ptr __aa, const xdc_UChar *__pa, xdc_SizeT __psz, xdc_runtime_Error_Block *__eb );

 

extern xdc_runtime_Main_Module_GateProxy_Handle xdc_runtime_Main_Module_GateProxy_create( const xdc_runtime_Main_Module_GateProxy_Params *__prms, xdc_runtime_Error_Block *__eb );

 

extern void xdc_runtime_Main_Module_GateProxy_Object__delete__S( xdc_Ptr instp );

 

extern void xdc_runtime_Main_Module_GateProxy_delete(xdc_runtime_Main_Module_GateProxy_Handle *instp);

 

extern void xdc_runtime_Main_Module_GateProxy_Object__destruct__S( xdc_Ptr objp );

 

extern xdc_Ptr xdc_runtime_Main_Module_GateProxy_Object__get__S( xdc_Ptr oarr, xdc_Int i );

 

extern xdc_Ptr xdc_runtime_Main_Module_GateProxy_Object__first__S( void );

 

extern xdc_Ptr xdc_runtime_Main_Module_GateProxy_Object__next__S( xdc_Ptr obj );

 

extern void xdc_runtime_Main_Module_GateProxy_Params__init__S( xdc_Ptr dst, const void *src, xdc_SizeT psz, xdc_SizeT isz );

 

extern xdc_Bool xdc_runtime_Main_Module_GateProxy_Proxy__abstract__S( void );

 

extern xdc_Ptr xdc_runtime_Main_Module_GateProxy_Proxy__delegate__S( void );

 

extern xdc_Bool xdc_runtime_Main_Module_GateProxy_query__E( xdc_Int qual );

 

extern xdc_IArg xdc_runtime_Main_Module_GateProxy_enter__E( xdc_runtime_Main_Module_GateProxy_Handle __inst );

 

extern void xdc_runtime_Main_Module_GateProxy_leave__E( xdc_runtime_Main_Module_GateProxy_Handle __inst, xdc_IArg key );




 

 
static inline xdc_runtime_IGateProvider_Module xdc_runtime_Main_Module_GateProxy_Module_upCast( void )
{
    return (xdc_runtime_IGateProvider_Module)xdc_runtime_Main_Module_GateProxy_Proxy__delegate__S();
}

 

 
static inline xdc_runtime_IGateProvider_Handle xdc_runtime_Main_Module_GateProxy_Handle_upCast( xdc_runtime_Main_Module_GateProxy_Handle i )
{
    return (xdc_runtime_IGateProvider_Handle)i;
}

 

 
static inline xdc_runtime_Main_Module_GateProxy_Handle xdc_runtime_Main_Module_GateProxy_Handle_downCast( xdc_runtime_IGateProvider_Handle i )
{
    xdc_runtime_IGateProvider_Handle i2 = (xdc_runtime_IGateProvider_Handle)i;
if (xdc_runtime_Main_Module_GateProxy_Proxy__abstract__S()) return (xdc_runtime_Main_Module_GateProxy_Handle)i;
    return (void*)i2->__fxns == (void*)xdc_runtime_Main_Module_GateProxy_Proxy__delegate__S() ? (xdc_runtime_Main_Module_GateProxy_Handle)i : 0;
}

 




 

 

 

 

 
static inline CT__xdc_runtime_Main_Module_GateProxy_Module__id xdc_runtime_Main_Module_GateProxy_Module_id( void ) 
{
    return xdc_runtime_Main_Module_GateProxy_Module__id__C;
}

 

 

 
static inline void xdc_runtime_Main_Module_GateProxy_Params_init( xdc_runtime_Main_Module_GateProxy_Params *prms ) 
{
    if (prms) {
        xdc_runtime_Main_Module_GateProxy_Params__init__S(prms, 0, sizeof(xdc_runtime_Main_Module_GateProxy_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 
static inline void xdc_runtime_Main_Module_GateProxy_Params_copy(xdc_runtime_Main_Module_GateProxy_Params *dst, const xdc_runtime_Main_Module_GateProxy_Params *src) 
{
    if (dst) {
        xdc_runtime_Main_Module_GateProxy_Params__init__S(dst, (const void *)src, sizeof(xdc_runtime_Main_Module_GateProxy_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}




 






 





 




 




 

 
typedef xdc_Bits32 CT__xdc_runtime_Main_Module__diagsEnabled;
extern  const CT__xdc_runtime_Main_Module__diagsEnabled xdc_runtime_Main_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__xdc_runtime_Main_Module__diagsIncluded;
extern  const CT__xdc_runtime_Main_Module__diagsIncluded xdc_runtime_Main_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__xdc_runtime_Main_Module__diagsMask;
extern  const CT__xdc_runtime_Main_Module__diagsMask xdc_runtime_Main_Module__diagsMask__C;

 
typedef xdc_Ptr CT__xdc_runtime_Main_Module__gateObj;
extern  const CT__xdc_runtime_Main_Module__gateObj xdc_runtime_Main_Module__gateObj__C;

 
typedef xdc_Ptr CT__xdc_runtime_Main_Module__gatePrms;
extern  const CT__xdc_runtime_Main_Module__gatePrms xdc_runtime_Main_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__xdc_runtime_Main_Module__id;
extern  const CT__xdc_runtime_Main_Module__id xdc_runtime_Main_Module__id__C;

 
typedef xdc_Bool CT__xdc_runtime_Main_Module__loggerDefined;
extern  const CT__xdc_runtime_Main_Module__loggerDefined xdc_runtime_Main_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__xdc_runtime_Main_Module__loggerObj;
extern  const CT__xdc_runtime_Main_Module__loggerObj xdc_runtime_Main_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__xdc_runtime_Main_Module__loggerFxn0;
extern  const CT__xdc_runtime_Main_Module__loggerFxn0 xdc_runtime_Main_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__xdc_runtime_Main_Module__loggerFxn1;
extern  const CT__xdc_runtime_Main_Module__loggerFxn1 xdc_runtime_Main_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__xdc_runtime_Main_Module__loggerFxn2;
extern  const CT__xdc_runtime_Main_Module__loggerFxn2 xdc_runtime_Main_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__xdc_runtime_Main_Module__loggerFxn4;
extern  const CT__xdc_runtime_Main_Module__loggerFxn4 xdc_runtime_Main_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__xdc_runtime_Main_Module__loggerFxn8;
extern  const CT__xdc_runtime_Main_Module__loggerFxn8 xdc_runtime_Main_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__xdc_runtime_Main_Module__startupDoneFxn)(void);
extern  const CT__xdc_runtime_Main_Module__startupDoneFxn xdc_runtime_Main_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__xdc_runtime_Main_Object__count;
extern  const CT__xdc_runtime_Main_Object__count xdc_runtime_Main_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__xdc_runtime_Main_Object__heap;
extern  const CT__xdc_runtime_Main_Object__heap xdc_runtime_Main_Object__heap__C;

 
typedef xdc_SizeT CT__xdc_runtime_Main_Object__sizeof;
extern  const CT__xdc_runtime_Main_Object__sizeof xdc_runtime_Main_Object__sizeof__C;

 
typedef xdc_Ptr CT__xdc_runtime_Main_Object__table;
extern  const CT__xdc_runtime_Main_Object__table xdc_runtime_Main_Object__table__C;




 

 

 

extern xdc_Bool xdc_runtime_Main_Module__startupDone__S( void );




 

 

 

 

 
static inline CT__xdc_runtime_Main_Module__id xdc_runtime_Main_Module_id( void ) 
{
    return xdc_runtime_Main_Module__id__C;
}

 
static inline xdc_Bool xdc_runtime_Main_Module_hasMask( void ) 
{
    return xdc_runtime_Main_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 xdc_runtime_Main_Module_getMask( void ) 
{
    return xdc_runtime_Main_Module__diagsMask__C != 0 ? *xdc_runtime_Main_Module__diagsMask__C : 0;
}

 
static inline void xdc_runtime_Main_Module_setMask( xdc_Bits16 mask ) 
{
    if (xdc_runtime_Main_Module__diagsMask__C != 0) *xdc_runtime_Main_Module__diagsMask__C = mask;
}




 






 




 



 




 
typedef xdc_Bits32 xdc_runtime_Error_Id;













 




 







 













 




 





 



 






 















 




 





 




 



 





 

 
enum xdc_runtime_Error_Policy {
    xdc_runtime_Error_TERMINATE,
    xdc_runtime_Error_UNWIND
};
typedef enum xdc_runtime_Error_Policy xdc_runtime_Error_Policy;

 

 
typedef void (*xdc_runtime_Error_HookFxn)(xdc_runtime_Error_Block*);

 

 
typedef xdc_IArg __T1_xdc_runtime_Error_Data__arg;
typedef xdc_IArg __ARRAY1_xdc_runtime_Error_Data__arg[2];
typedef __ARRAY1_xdc_runtime_Error_Data__arg __TA_xdc_runtime_Error_Data__arg;
struct xdc_runtime_Error_Data {
    __TA_xdc_runtime_Error_Data__arg arg;
};

 
typedef xdc_IArg __T1_xdc_runtime_Error_Block__xtra;
typedef xdc_IArg __ARRAY1_xdc_runtime_Error_Block__xtra[4];
typedef __ARRAY1_xdc_runtime_Error_Block__xtra __TA_xdc_runtime_Error_Block__xtra;
struct xdc_runtime_Error_Block {
    xdc_UInt16 unused;
    xdc_runtime_Error_Data data;
    xdc_runtime_Error_Id id;
    xdc_String msg;
    xdc_runtime_Types_Site site;
    __TA_xdc_runtime_Error_Block__xtra xtra;
};

 
typedef void (*xdc_runtime_Error_PolicyFxn)(xdc_runtime_Error_Block*, xdc_runtime_Types_ModuleId, xdc_CString, xdc_Int, xdc_runtime_Error_Id, xdc_IArg, xdc_IArg);




 




 

 
typedef xdc_Bits32 CT__xdc_runtime_Error_Module__diagsEnabled;
extern  const CT__xdc_runtime_Error_Module__diagsEnabled xdc_runtime_Error_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__xdc_runtime_Error_Module__diagsIncluded;
extern  const CT__xdc_runtime_Error_Module__diagsIncluded xdc_runtime_Error_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__xdc_runtime_Error_Module__diagsMask;
extern  const CT__xdc_runtime_Error_Module__diagsMask xdc_runtime_Error_Module__diagsMask__C;

 
typedef xdc_Ptr CT__xdc_runtime_Error_Module__gateObj;
extern  const CT__xdc_runtime_Error_Module__gateObj xdc_runtime_Error_Module__gateObj__C;

 
typedef xdc_Ptr CT__xdc_runtime_Error_Module__gatePrms;
extern  const CT__xdc_runtime_Error_Module__gatePrms xdc_runtime_Error_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__xdc_runtime_Error_Module__id;
extern  const CT__xdc_runtime_Error_Module__id xdc_runtime_Error_Module__id__C;

 
typedef xdc_Bool CT__xdc_runtime_Error_Module__loggerDefined;
extern  const CT__xdc_runtime_Error_Module__loggerDefined xdc_runtime_Error_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__xdc_runtime_Error_Module__loggerObj;
extern  const CT__xdc_runtime_Error_Module__loggerObj xdc_runtime_Error_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__xdc_runtime_Error_Module__loggerFxn0;
extern  const CT__xdc_runtime_Error_Module__loggerFxn0 xdc_runtime_Error_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__xdc_runtime_Error_Module__loggerFxn1;
extern  const CT__xdc_runtime_Error_Module__loggerFxn1 xdc_runtime_Error_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__xdc_runtime_Error_Module__loggerFxn2;
extern  const CT__xdc_runtime_Error_Module__loggerFxn2 xdc_runtime_Error_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__xdc_runtime_Error_Module__loggerFxn4;
extern  const CT__xdc_runtime_Error_Module__loggerFxn4 xdc_runtime_Error_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__xdc_runtime_Error_Module__loggerFxn8;
extern  const CT__xdc_runtime_Error_Module__loggerFxn8 xdc_runtime_Error_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__xdc_runtime_Error_Module__startupDoneFxn)(void);
extern  const CT__xdc_runtime_Error_Module__startupDoneFxn xdc_runtime_Error_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__xdc_runtime_Error_Object__count;
extern  const CT__xdc_runtime_Error_Object__count xdc_runtime_Error_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__xdc_runtime_Error_Object__heap;
extern  const CT__xdc_runtime_Error_Object__heap xdc_runtime_Error_Object__heap__C;

 
typedef xdc_SizeT CT__xdc_runtime_Error_Object__sizeof;
extern  const CT__xdc_runtime_Error_Object__sizeof xdc_runtime_Error_Object__sizeof__C;

 
typedef xdc_Ptr CT__xdc_runtime_Error_Object__table;
extern  const CT__xdc_runtime_Error_Object__table xdc_runtime_Error_Object__table__C;

 
typedef xdc_runtime_Error_PolicyFxn CT__xdc_runtime_Error_policyFxn;
extern  const CT__xdc_runtime_Error_policyFxn xdc_runtime_Error_policyFxn__C;

 
typedef xdc_runtime_Error_Id CT__xdc_runtime_Error_E_generic;
extern  const CT__xdc_runtime_Error_E_generic xdc_runtime_Error_E_generic__C;

 
typedef xdc_runtime_Error_Id CT__xdc_runtime_Error_E_memory;
extern  const CT__xdc_runtime_Error_E_memory xdc_runtime_Error_E_memory__C;

 
typedef xdc_runtime_Error_Id CT__xdc_runtime_Error_E_msgCode;
extern  const CT__xdc_runtime_Error_E_msgCode xdc_runtime_Error_E_msgCode__C;

 
typedef xdc_runtime_Error_Policy CT__xdc_runtime_Error_policy;
extern  const CT__xdc_runtime_Error_policy xdc_runtime_Error_policy__C;

 
typedef xdc_runtime_Error_HookFxn CT__xdc_runtime_Error_raiseHook;
extern  const CT__xdc_runtime_Error_raiseHook xdc_runtime_Error_raiseHook__C;

 
typedef xdc_UInt16 CT__xdc_runtime_Error_maxDepth;
extern  const CT__xdc_runtime_Error_maxDepth xdc_runtime_Error_maxDepth__C;




 

 

 

extern xdc_Bool xdc_runtime_Error_Module__startupDone__S( void );

 

extern xdc_Bool xdc_runtime_Error_check__E( xdc_runtime_Error_Block *eb );

 

extern xdc_runtime_Error_Data *xdc_runtime_Error_getData__E( xdc_runtime_Error_Block *eb );

 

extern xdc_UInt16 xdc_runtime_Error_getCode__E( xdc_runtime_Error_Block *eb );

 

extern xdc_runtime_Error_Id xdc_runtime_Error_getId__E( xdc_runtime_Error_Block *eb );

 

extern xdc_String xdc_runtime_Error_getMsg__E( xdc_runtime_Error_Block *eb );

 

extern xdc_runtime_Types_Site *xdc_runtime_Error_getSite__E( xdc_runtime_Error_Block *eb );

 

extern void xdc_runtime_Error_init__E( xdc_runtime_Error_Block *eb );

 

extern void xdc_runtime_Error_print__E( xdc_runtime_Error_Block *eb );

 

extern void xdc_runtime_Error_policyDefault__E( xdc_runtime_Error_Block *eb, xdc_runtime_Types_ModuleId mod, xdc_CString file, xdc_Int line, xdc_runtime_Error_Id id, xdc_IArg arg1, xdc_IArg arg2 );

 

extern void xdc_runtime_Error_policySpin__E( xdc_runtime_Error_Block *eb, xdc_runtime_Types_ModuleId mod, xdc_CString file, xdc_Int line, xdc_runtime_Error_Id id, xdc_IArg arg1, xdc_IArg arg2 );

 

extern void xdc_runtime_Error_raiseX__E( xdc_runtime_Error_Block *eb, xdc_runtime_Types_ModuleId mod, xdc_CString file, xdc_Int line, xdc_runtime_Error_Id id, xdc_IArg arg1, xdc_IArg arg2 );

 

extern void xdc_runtime_Error_setX__E( xdc_runtime_Error_Block *eb, xdc_runtime_Types_ModuleId mod, xdc_CString file, xdc_Int line, xdc_runtime_Error_Id id, xdc_IArg arg1, xdc_IArg arg2 );




 

 

 

 

 
static inline CT__xdc_runtime_Error_Module__id xdc_runtime_Error_Module_id( void ) 
{
    return xdc_runtime_Error_Module__id__C;
}

 
static inline xdc_Bool xdc_runtime_Error_Module_hasMask( void ) 
{
    return xdc_runtime_Error_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 xdc_runtime_Error_Module_getMask( void ) 
{
    return xdc_runtime_Error_Module__diagsMask__C != 0 ? *xdc_runtime_Error_Module__diagsMask__C : 0;
}

 
static inline void xdc_runtime_Error_Module_setMask( xdc_Bits16 mask ) 
{
    if (xdc_runtime_Error_Module__diagsMask__C != 0) *xdc_runtime_Error_Module__diagsMask__C = mask;
}




 












 








 



 



 


 







 





 



 






 















 




 





 




 



 






 













 




 





 



 






 














 




 





 



 






 
















 




 








 







 















 




 





 




 



 






 














 




 





 



 







 














 




 





 



 





 

 

 




 

 
typedef xdc_Bits32 CT__ti_sysbios_BIOS_RtsGateProxy_Module__diagsEnabled;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Module__diagsEnabled ti_sysbios_BIOS_RtsGateProxy_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__ti_sysbios_BIOS_RtsGateProxy_Module__diagsIncluded;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Module__diagsIncluded ti_sysbios_BIOS_RtsGateProxy_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__ti_sysbios_BIOS_RtsGateProxy_Module__diagsMask;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Module__diagsMask ti_sysbios_BIOS_RtsGateProxy_Module__diagsMask__C;

 
typedef xdc_Ptr CT__ti_sysbios_BIOS_RtsGateProxy_Module__gateObj;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Module__gateObj ti_sysbios_BIOS_RtsGateProxy_Module__gateObj__C;

 
typedef xdc_Ptr CT__ti_sysbios_BIOS_RtsGateProxy_Module__gatePrms;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Module__gatePrms ti_sysbios_BIOS_RtsGateProxy_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__ti_sysbios_BIOS_RtsGateProxy_Module__id;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Module__id ti_sysbios_BIOS_RtsGateProxy_Module__id__C;

 
typedef xdc_Bool CT__ti_sysbios_BIOS_RtsGateProxy_Module__loggerDefined;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Module__loggerDefined ti_sysbios_BIOS_RtsGateProxy_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__ti_sysbios_BIOS_RtsGateProxy_Module__loggerObj;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Module__loggerObj ti_sysbios_BIOS_RtsGateProxy_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn0;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn0 ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn1;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn1 ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn2;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn2 ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn4;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn4 ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn8;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn8 ti_sysbios_BIOS_RtsGateProxy_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__ti_sysbios_BIOS_RtsGateProxy_Module__startupDoneFxn)(void);
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Module__startupDoneFxn ti_sysbios_BIOS_RtsGateProxy_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__ti_sysbios_BIOS_RtsGateProxy_Object__count;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Object__count ti_sysbios_BIOS_RtsGateProxy_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__ti_sysbios_BIOS_RtsGateProxy_Object__heap;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Object__heap ti_sysbios_BIOS_RtsGateProxy_Object__heap__C;

 
typedef xdc_SizeT CT__ti_sysbios_BIOS_RtsGateProxy_Object__sizeof;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Object__sizeof ti_sysbios_BIOS_RtsGateProxy_Object__sizeof__C;

 
typedef xdc_Ptr CT__ti_sysbios_BIOS_RtsGateProxy_Object__table;
extern  const CT__ti_sysbios_BIOS_RtsGateProxy_Object__table ti_sysbios_BIOS_RtsGateProxy_Object__table__C;




 

 
struct ti_sysbios_BIOS_RtsGateProxy_Params {
    size_t __size;
    const void *__self;
    void *__fxns;
    xdc_runtime_IInstance_Params *instance;
    xdc_runtime_IInstance_Params __iprms;
};

 
struct ti_sysbios_BIOS_RtsGateProxy_Struct {
    const ti_sysbios_BIOS_RtsGateProxy_Fxns__ *__fxns;
    xdc_runtime_Types_CordAddr __name;
};




 

 
struct ti_sysbios_BIOS_RtsGateProxy_Fxns__ {
    xdc_runtime_Types_Base* __base;
    const xdc_runtime_Types_SysFxns2 *__sysp;
    xdc_Bool (*query)(xdc_Int);
    xdc_IArg (*enter)(ti_sysbios_BIOS_RtsGateProxy_Handle);
    void (*leave)(ti_sysbios_BIOS_RtsGateProxy_Handle, xdc_IArg);
    xdc_runtime_Types_SysFxns2 __sfxns;
};

 
extern const ti_sysbios_BIOS_RtsGateProxy_Fxns__ ti_sysbios_BIOS_RtsGateProxy_Module__FXNS__C;




 

 

 

extern xdc_runtime_Types_Label *ti_sysbios_BIOS_RtsGateProxy_Handle__label__S( xdc_Ptr obj, xdc_runtime_Types_Label *lab );

 

extern xdc_Bool ti_sysbios_BIOS_RtsGateProxy_Module__startupDone__S( void );

 

extern xdc_Ptr ti_sysbios_BIOS_RtsGateProxy_Object__create__S( xdc_Ptr __oa, xdc_SizeT __osz, xdc_Ptr __aa, const xdc_UChar *__pa, xdc_SizeT __psz, xdc_runtime_Error_Block *__eb );

 

extern ti_sysbios_BIOS_RtsGateProxy_Handle ti_sysbios_BIOS_RtsGateProxy_create( const ti_sysbios_BIOS_RtsGateProxy_Params *__prms, xdc_runtime_Error_Block *__eb );

 

extern void ti_sysbios_BIOS_RtsGateProxy_Object__delete__S( xdc_Ptr instp );

 

extern void ti_sysbios_BIOS_RtsGateProxy_delete(ti_sysbios_BIOS_RtsGateProxy_Handle *instp);

 

extern void ti_sysbios_BIOS_RtsGateProxy_Object__destruct__S( xdc_Ptr objp );

 

extern xdc_Ptr ti_sysbios_BIOS_RtsGateProxy_Object__get__S( xdc_Ptr oarr, xdc_Int i );

 

extern xdc_Ptr ti_sysbios_BIOS_RtsGateProxy_Object__first__S( void );

 

extern xdc_Ptr ti_sysbios_BIOS_RtsGateProxy_Object__next__S( xdc_Ptr obj );

 

extern void ti_sysbios_BIOS_RtsGateProxy_Params__init__S( xdc_Ptr dst, const void *src, xdc_SizeT psz, xdc_SizeT isz );

 

extern xdc_Bool ti_sysbios_BIOS_RtsGateProxy_Proxy__abstract__S( void );

 

extern xdc_Ptr ti_sysbios_BIOS_RtsGateProxy_Proxy__delegate__S( void );

 

extern xdc_Bool ti_sysbios_BIOS_RtsGateProxy_query__E( xdc_Int qual );

 

extern xdc_IArg ti_sysbios_BIOS_RtsGateProxy_enter__E( ti_sysbios_BIOS_RtsGateProxy_Handle __inst );

 

extern void ti_sysbios_BIOS_RtsGateProxy_leave__E( ti_sysbios_BIOS_RtsGateProxy_Handle __inst, xdc_IArg key );




 

 
static inline xdc_runtime_IGateProvider_Module ti_sysbios_BIOS_RtsGateProxy_Module_upCast( void )
{
    return (xdc_runtime_IGateProvider_Module)ti_sysbios_BIOS_RtsGateProxy_Proxy__delegate__S();
}

 

 
static inline xdc_runtime_IGateProvider_Handle ti_sysbios_BIOS_RtsGateProxy_Handle_upCast( ti_sysbios_BIOS_RtsGateProxy_Handle i )
{
    return (xdc_runtime_IGateProvider_Handle)i;
}

 

 
static inline ti_sysbios_BIOS_RtsGateProxy_Handle ti_sysbios_BIOS_RtsGateProxy_Handle_downCast( xdc_runtime_IGateProvider_Handle i )
{
    xdc_runtime_IGateProvider_Handle i2 = (xdc_runtime_IGateProvider_Handle)i;
if (ti_sysbios_BIOS_RtsGateProxy_Proxy__abstract__S()) return (ti_sysbios_BIOS_RtsGateProxy_Handle)i;
    return (void*)i2->__fxns == (void*)ti_sysbios_BIOS_RtsGateProxy_Proxy__delegate__S() ? (ti_sysbios_BIOS_RtsGateProxy_Handle)i : 0;
}

 




 

 

 

 

 
static inline CT__ti_sysbios_BIOS_RtsGateProxy_Module__id ti_sysbios_BIOS_RtsGateProxy_Module_id( void ) 
{
    return ti_sysbios_BIOS_RtsGateProxy_Module__id__C;
}

 

 

 
static inline void ti_sysbios_BIOS_RtsGateProxy_Params_init( ti_sysbios_BIOS_RtsGateProxy_Params *prms ) 
{
    if (prms) {
        ti_sysbios_BIOS_RtsGateProxy_Params__init__S(prms, 0, sizeof(ti_sysbios_BIOS_RtsGateProxy_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 
static inline void ti_sysbios_BIOS_RtsGateProxy_Params_copy(ti_sysbios_BIOS_RtsGateProxy_Params *dst, const ti_sysbios_BIOS_RtsGateProxy_Params *src) 
{
    if (dst) {
        ti_sysbios_BIOS_RtsGateProxy_Params__init__S(dst, (const void *)src, sizeof(ti_sysbios_BIOS_RtsGateProxy_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}




 






 





 

 
enum ti_sysbios_BIOS_ThreadType {
    ti_sysbios_BIOS_ThreadType_Hwi,
    ti_sysbios_BIOS_ThreadType_Swi,
    ti_sysbios_BIOS_ThreadType_Task,
    ti_sysbios_BIOS_ThreadType_Main
};
typedef enum ti_sysbios_BIOS_ThreadType ti_sysbios_BIOS_ThreadType;

 
enum ti_sysbios_BIOS_RtsLockType {
    ti_sysbios_BIOS_NoLocking,
    ti_sysbios_BIOS_GateHwi,
    ti_sysbios_BIOS_GateSwi,
    ti_sysbios_BIOS_GateMutex,
    ti_sysbios_BIOS_GateMutexPri
};
typedef enum ti_sysbios_BIOS_RtsLockType ti_sysbios_BIOS_RtsLockType;

 
enum ti_sysbios_BIOS_LibType {
    ti_sysbios_BIOS_LibType_Instrumented,
    ti_sysbios_BIOS_LibType_NonInstrumented,
    ti_sysbios_BIOS_LibType_Custom,
    ti_sysbios_BIOS_LibType_Debug
};
typedef enum ti_sysbios_BIOS_LibType ti_sysbios_BIOS_LibType;

 

 

 
typedef void (*ti_sysbios_BIOS_StartupFuncPtr)(void);

 




 

 
struct ti_sysbios_BIOS_intSize {
    xdc_Int intSize;
};

 
typedef void (*ti_sysbios_BIOS_StartFuncPtr)(void);

 
typedef void (*ti_sysbios_BIOS_ExitFuncPtr)(xdc_Int);

 
typedef ti_sysbios_BIOS_ThreadType __T1_ti_sysbios_BIOS_Module_State__smpThreadType;
typedef ti_sysbios_BIOS_ThreadType *__ARRAY1_ti_sysbios_BIOS_Module_State__smpThreadType;
typedef __ARRAY1_ti_sysbios_BIOS_Module_State__smpThreadType __TA_ti_sysbios_BIOS_Module_State__smpThreadType;




 

 
typedef xdc_Bits32 CT__ti_sysbios_BIOS_Module__diagsEnabled;
extern  const CT__ti_sysbios_BIOS_Module__diagsEnabled ti_sysbios_BIOS_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__ti_sysbios_BIOS_Module__diagsIncluded;
extern  const CT__ti_sysbios_BIOS_Module__diagsIncluded ti_sysbios_BIOS_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__ti_sysbios_BIOS_Module__diagsMask;
extern  const CT__ti_sysbios_BIOS_Module__diagsMask ti_sysbios_BIOS_Module__diagsMask__C;

 
typedef xdc_Ptr CT__ti_sysbios_BIOS_Module__gateObj;
extern  const CT__ti_sysbios_BIOS_Module__gateObj ti_sysbios_BIOS_Module__gateObj__C;

 
typedef xdc_Ptr CT__ti_sysbios_BIOS_Module__gatePrms;
extern  const CT__ti_sysbios_BIOS_Module__gatePrms ti_sysbios_BIOS_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__ti_sysbios_BIOS_Module__id;
extern  const CT__ti_sysbios_BIOS_Module__id ti_sysbios_BIOS_Module__id__C;

 
typedef xdc_Bool CT__ti_sysbios_BIOS_Module__loggerDefined;
extern  const CT__ti_sysbios_BIOS_Module__loggerDefined ti_sysbios_BIOS_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__ti_sysbios_BIOS_Module__loggerObj;
extern  const CT__ti_sysbios_BIOS_Module__loggerObj ti_sysbios_BIOS_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__ti_sysbios_BIOS_Module__loggerFxn0;
extern  const CT__ti_sysbios_BIOS_Module__loggerFxn0 ti_sysbios_BIOS_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__ti_sysbios_BIOS_Module__loggerFxn1;
extern  const CT__ti_sysbios_BIOS_Module__loggerFxn1 ti_sysbios_BIOS_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__ti_sysbios_BIOS_Module__loggerFxn2;
extern  const CT__ti_sysbios_BIOS_Module__loggerFxn2 ti_sysbios_BIOS_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__ti_sysbios_BIOS_Module__loggerFxn4;
extern  const CT__ti_sysbios_BIOS_Module__loggerFxn4 ti_sysbios_BIOS_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__ti_sysbios_BIOS_Module__loggerFxn8;
extern  const CT__ti_sysbios_BIOS_Module__loggerFxn8 ti_sysbios_BIOS_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__ti_sysbios_BIOS_Module__startupDoneFxn)(void);
extern  const CT__ti_sysbios_BIOS_Module__startupDoneFxn ti_sysbios_BIOS_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__ti_sysbios_BIOS_Object__count;
extern  const CT__ti_sysbios_BIOS_Object__count ti_sysbios_BIOS_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__ti_sysbios_BIOS_Object__heap;
extern  const CT__ti_sysbios_BIOS_Object__heap ti_sysbios_BIOS_Object__heap__C;

 
typedef xdc_SizeT CT__ti_sysbios_BIOS_Object__sizeof;
extern  const CT__ti_sysbios_BIOS_Object__sizeof ti_sysbios_BIOS_Object__sizeof__C;

 
typedef xdc_Ptr CT__ti_sysbios_BIOS_Object__table;
extern  const CT__ti_sysbios_BIOS_Object__table ti_sysbios_BIOS_Object__table__C;

 
typedef xdc_Bool CT__ti_sysbios_BIOS_smpEnabled;
extern  const CT__ti_sysbios_BIOS_smpEnabled ti_sysbios_BIOS_smpEnabled__C;

 
typedef xdc_runtime_Types_FreqHz CT__ti_sysbios_BIOS_cpuFreq;
extern  const CT__ti_sysbios_BIOS_cpuFreq ti_sysbios_BIOS_cpuFreq__C;

 
typedef xdc_Bool CT__ti_sysbios_BIOS_runtimeCreatesEnabled;
extern  const CT__ti_sysbios_BIOS_runtimeCreatesEnabled ti_sysbios_BIOS_runtimeCreatesEnabled__C;

 
typedef xdc_Bool CT__ti_sysbios_BIOS_taskEnabled;
extern  const CT__ti_sysbios_BIOS_taskEnabled ti_sysbios_BIOS_taskEnabled__C;

 
typedef xdc_Bool CT__ti_sysbios_BIOS_swiEnabled;
extern  const CT__ti_sysbios_BIOS_swiEnabled ti_sysbios_BIOS_swiEnabled__C;

 
typedef xdc_Bool CT__ti_sysbios_BIOS_clockEnabled;
extern  const CT__ti_sysbios_BIOS_clockEnabled ti_sysbios_BIOS_clockEnabled__C;

 
typedef xdc_SizeT CT__ti_sysbios_BIOS_heapSize;
extern  const CT__ti_sysbios_BIOS_heapSize ti_sysbios_BIOS_heapSize__C;

 
typedef xdc_String CT__ti_sysbios_BIOS_heapSection;
extern  const CT__ti_sysbios_BIOS_heapSection ti_sysbios_BIOS_heapSection__C;

 
typedef xdc_Bool CT__ti_sysbios_BIOS_heapTrackEnabled;
extern  const CT__ti_sysbios_BIOS_heapTrackEnabled ti_sysbios_BIOS_heapTrackEnabled__C;

 
typedef xdc_Bool CT__ti_sysbios_BIOS_setupSecureContext;
extern  const CT__ti_sysbios_BIOS_setupSecureContext ti_sysbios_BIOS_setupSecureContext__C;

 
typedef xdc_Bool CT__ti_sysbios_BIOS_useSK;
extern  const CT__ti_sysbios_BIOS_useSK ti_sysbios_BIOS_useSK__C;

 
typedef void (*CT__ti_sysbios_BIOS_installedErrorHook)(xdc_runtime_Error_Block*);
extern  const CT__ti_sysbios_BIOS_installedErrorHook ti_sysbios_BIOS_installedErrorHook__C;




 

 

 

extern xdc_Bool ti_sysbios_BIOS_Module__startupDone__S( void );

 

extern void ti_sysbios_BIOS_start__E( void );

 

extern void ti_sysbios_BIOS_exit__E( xdc_Int stat );

 

extern ti_sysbios_BIOS_ThreadType ti_sysbios_BIOS_getThreadType__E( void );

 

extern ti_sysbios_BIOS_ThreadType ti_sysbios_BIOS_setThreadType__E( ti_sysbios_BIOS_ThreadType ttype );

 

extern void ti_sysbios_BIOS_setCpuFreq__E( xdc_runtime_Types_FreqHz *freq );

 

extern void ti_sysbios_BIOS_getCpuFreq__E( xdc_runtime_Types_FreqHz *freq );

 

extern void ti_sysbios_BIOS_errorRaiseHook__I( xdc_runtime_Error_Block *eb );

 

extern void ti_sysbios_BIOS_startFunc__I( void );

 

extern void ti_sysbios_BIOS_atExitFunc__I( xdc_Int stat );

 

extern void ti_sysbios_BIOS_exitFunc__I( xdc_Int stat );

 

extern void ti_sysbios_BIOS_registerRTSLock__I( void );

 

extern void ti_sysbios_BIOS_removeRTSLock__I( void );

 

extern void ti_sysbios_BIOS_rtsLock__I( void );

 

extern void ti_sysbios_BIOS_rtsUnlock__I( void );

 

extern void ti_sysbios_BIOS_nullFunc__I( void );




 

 

 

 

 
static inline CT__ti_sysbios_BIOS_Module__id ti_sysbios_BIOS_Module_id( void ) 
{
    return ti_sysbios_BIOS_Module__id__C;
}

 
static inline xdc_Bool ti_sysbios_BIOS_Module_hasMask( void ) 
{
    return ti_sysbios_BIOS_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 ti_sysbios_BIOS_Module_getMask( void ) 
{
    return ti_sysbios_BIOS_Module__diagsMask__C != 0 ? *ti_sysbios_BIOS_Module__diagsMask__C : 0;
}

 
static inline void ti_sysbios_BIOS_Module_setMask( xdc_Bits16 mask ) 
{
    if (ti_sysbios_BIOS_Module__diagsMask__C != 0) *ti_sysbios_BIOS_Module__diagsMask__C = mask;
}




 































 








 





 



 

 





 
















 




 





 



 







 

















 




 








 







 















 




 





 




 



 






 














 




 





 



 






 





 

typedef struct ti_sysbios_knl_Clock_Module_State ti_sysbios_knl_Clock_Module_State;
typedef struct ti_sysbios_knl_Clock_Params ti_sysbios_knl_Clock_Params;
typedef struct ti_sysbios_knl_Clock_Object ti_sysbios_knl_Clock_Object;
typedef struct ti_sysbios_knl_Clock_Struct ti_sysbios_knl_Clock_Struct;
typedef ti_sysbios_knl_Clock_Object* ti_sysbios_knl_Clock_Handle;
typedef struct ti_sysbios_knl_Clock_Object__ ti_sysbios_knl_Clock_Instance_State;
typedef ti_sysbios_knl_Clock_Object* ti_sysbios_knl_Clock_Instance;



 




 

typedef struct ti_sysbios_knl_Intrinsics_Fxns__ ti_sysbios_knl_Intrinsics_Fxns__;
typedef const ti_sysbios_knl_Intrinsics_Fxns__* ti_sysbios_knl_Intrinsics_Module;



 

typedef struct ti_sysbios_knl_Event_PendElem ti_sysbios_knl_Event_PendElem;
typedef struct ti_sysbios_knl_Event_Params ti_sysbios_knl_Event_Params;
typedef struct ti_sysbios_knl_Event_Object ti_sysbios_knl_Event_Object;
typedef struct ti_sysbios_knl_Event_Struct ti_sysbios_knl_Event_Struct;
typedef ti_sysbios_knl_Event_Object* ti_sysbios_knl_Event_Handle;
typedef struct ti_sysbios_knl_Event_Object__ ti_sysbios_knl_Event_Instance_State;
typedef ti_sysbios_knl_Event_Object* ti_sysbios_knl_Event_Instance;



 

typedef struct ti_sysbios_knl_Mailbox_MbxElem ti_sysbios_knl_Mailbox_MbxElem;
typedef struct ti_sysbios_knl_Mailbox_Params ti_sysbios_knl_Mailbox_Params;
typedef struct ti_sysbios_knl_Mailbox_Object ti_sysbios_knl_Mailbox_Object;
typedef struct ti_sysbios_knl_Mailbox_Struct ti_sysbios_knl_Mailbox_Struct;
typedef ti_sysbios_knl_Mailbox_Object* ti_sysbios_knl_Mailbox_Handle;
typedef struct ti_sysbios_knl_Mailbox_Object__ ti_sysbios_knl_Mailbox_Instance_State;
typedef ti_sysbios_knl_Mailbox_Object* ti_sysbios_knl_Mailbox_Instance;



 

typedef struct ti_sysbios_knl_Queue_Elem ti_sysbios_knl_Queue_Elem;
typedef struct ti_sysbios_knl_Queue_Params ti_sysbios_knl_Queue_Params;
typedef struct ti_sysbios_knl_Queue_Object ti_sysbios_knl_Queue_Object;
typedef struct ti_sysbios_knl_Queue_Struct ti_sysbios_knl_Queue_Struct;
typedef ti_sysbios_knl_Queue_Object* ti_sysbios_knl_Queue_Handle;
typedef struct ti_sysbios_knl_Queue_Object__ ti_sysbios_knl_Queue_Instance_State;
typedef ti_sysbios_knl_Queue_Object* ti_sysbios_knl_Queue_Instance;



 

typedef struct ti_sysbios_knl_Semaphore_PendElem ti_sysbios_knl_Semaphore_PendElem;
typedef struct ti_sysbios_knl_Semaphore_Params ti_sysbios_knl_Semaphore_Params;
typedef struct ti_sysbios_knl_Semaphore_Object ti_sysbios_knl_Semaphore_Object;
typedef struct ti_sysbios_knl_Semaphore_Struct ti_sysbios_knl_Semaphore_Struct;
typedef ti_sysbios_knl_Semaphore_Object* ti_sysbios_knl_Semaphore_Handle;
typedef struct ti_sysbios_knl_Semaphore_Object__ ti_sysbios_knl_Semaphore_Instance_State;
typedef ti_sysbios_knl_Semaphore_Object* ti_sysbios_knl_Semaphore_Instance;



 

typedef struct ti_sysbios_knl_Swi_HookSet ti_sysbios_knl_Swi_HookSet;
typedef struct ti_sysbios_knl_Swi_Module_State ti_sysbios_knl_Swi_Module_State;
typedef struct ti_sysbios_knl_Swi_Params ti_sysbios_knl_Swi_Params;
typedef struct ti_sysbios_knl_Swi_Object ti_sysbios_knl_Swi_Object;
typedef struct ti_sysbios_knl_Swi_Struct ti_sysbios_knl_Swi_Struct;
typedef ti_sysbios_knl_Swi_Object* ti_sysbios_knl_Swi_Handle;
typedef struct ti_sysbios_knl_Swi_Object__ ti_sysbios_knl_Swi_Instance_State;
typedef ti_sysbios_knl_Swi_Object* ti_sysbios_knl_Swi_Instance;



 

typedef struct ti_sysbios_knl_Task_Stat ti_sysbios_knl_Task_Stat;
typedef struct ti_sysbios_knl_Task_HookSet ti_sysbios_knl_Task_HookSet;
typedef struct ti_sysbios_knl_Task_PendElem ti_sysbios_knl_Task_PendElem;
typedef struct ti_sysbios_knl_Task_Module_State ti_sysbios_knl_Task_Module_State;
typedef struct ti_sysbios_knl_Task_RunQEntry ti_sysbios_knl_Task_RunQEntry;
typedef struct ti_sysbios_knl_Task_Module_StateSmp ti_sysbios_knl_Task_Module_StateSmp;
typedef struct ti_sysbios_knl_Task_Params ti_sysbios_knl_Task_Params;
typedef struct ti_sysbios_knl_Task_Object ti_sysbios_knl_Task_Object;
typedef struct ti_sysbios_knl_Task_Struct ti_sysbios_knl_Task_Struct;
typedef ti_sysbios_knl_Task_Object* ti_sysbios_knl_Task_Handle;
typedef struct ti_sysbios_knl_Task_Object__ ti_sysbios_knl_Task_Instance_State;
typedef ti_sysbios_knl_Task_Object* ti_sysbios_knl_Task_Instance;



 

typedef struct ti_sysbios_knl_Clock_TimerProxy_Fxns__ ti_sysbios_knl_Clock_TimerProxy_Fxns__;
typedef const ti_sysbios_knl_Clock_TimerProxy_Fxns__* ti_sysbios_knl_Clock_TimerProxy_Module;
typedef struct ti_sysbios_knl_Clock_TimerProxy_Params ti_sysbios_knl_Clock_TimerProxy_Params;
typedef struct ti_sysbios_interfaces_ITimer___Object *ti_sysbios_knl_Clock_TimerProxy_Handle;



 

typedef struct ti_sysbios_knl_Intrinsics_SupportProxy_Fxns__ ti_sysbios_knl_Intrinsics_SupportProxy_Fxns__;
typedef const ti_sysbios_knl_Intrinsics_SupportProxy_Fxns__* ti_sysbios_knl_Intrinsics_SupportProxy_Module;



 

typedef struct ti_sysbios_knl_Task_SupportProxy_Fxns__ ti_sysbios_knl_Task_SupportProxy_Fxns__;
typedef const ti_sysbios_knl_Task_SupportProxy_Fxns__* ti_sysbios_knl_Task_SupportProxy_Module;








 















 




 








 







 















 




 





 




 



 












 









 





 















 




 





 




 



 







 








 








 




 



 



 







 













 




 





 



 






 















 




 





 




 



 





 

 
typedef xdc_runtime_Types_DiagsMask xdc_runtime_Diags_Mask;

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 
enum xdc_runtime_Diags_EventLevel {
    xdc_runtime_Diags_LEVEL1 = 0x0,
    xdc_runtime_Diags_LEVEL2 = 0x20,
    xdc_runtime_Diags_LEVEL3 = 0x40,
    xdc_runtime_Diags_LEVEL4 = 0x60
};
typedef enum xdc_runtime_Diags_EventLevel xdc_runtime_Diags_EventLevel;

 

 

 

 




 

 
struct xdc_runtime_Diags_DictElem {
    xdc_runtime_Types_ModuleId modId;
    xdc_runtime_Diags_Mask *maskAddr;
};




 

 
typedef xdc_Bits32 CT__xdc_runtime_Diags_Module__diagsEnabled;
extern  const CT__xdc_runtime_Diags_Module__diagsEnabled xdc_runtime_Diags_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__xdc_runtime_Diags_Module__diagsIncluded;
extern  const CT__xdc_runtime_Diags_Module__diagsIncluded xdc_runtime_Diags_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__xdc_runtime_Diags_Module__diagsMask;
extern  const CT__xdc_runtime_Diags_Module__diagsMask xdc_runtime_Diags_Module__diagsMask__C;

 
typedef xdc_Ptr CT__xdc_runtime_Diags_Module__gateObj;
extern  const CT__xdc_runtime_Diags_Module__gateObj xdc_runtime_Diags_Module__gateObj__C;

 
typedef xdc_Ptr CT__xdc_runtime_Diags_Module__gatePrms;
extern  const CT__xdc_runtime_Diags_Module__gatePrms xdc_runtime_Diags_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__xdc_runtime_Diags_Module__id;
extern  const CT__xdc_runtime_Diags_Module__id xdc_runtime_Diags_Module__id__C;

 
typedef xdc_Bool CT__xdc_runtime_Diags_Module__loggerDefined;
extern  const CT__xdc_runtime_Diags_Module__loggerDefined xdc_runtime_Diags_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__xdc_runtime_Diags_Module__loggerObj;
extern  const CT__xdc_runtime_Diags_Module__loggerObj xdc_runtime_Diags_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__xdc_runtime_Diags_Module__loggerFxn0;
extern  const CT__xdc_runtime_Diags_Module__loggerFxn0 xdc_runtime_Diags_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__xdc_runtime_Diags_Module__loggerFxn1;
extern  const CT__xdc_runtime_Diags_Module__loggerFxn1 xdc_runtime_Diags_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__xdc_runtime_Diags_Module__loggerFxn2;
extern  const CT__xdc_runtime_Diags_Module__loggerFxn2 xdc_runtime_Diags_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__xdc_runtime_Diags_Module__loggerFxn4;
extern  const CT__xdc_runtime_Diags_Module__loggerFxn4 xdc_runtime_Diags_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__xdc_runtime_Diags_Module__loggerFxn8;
extern  const CT__xdc_runtime_Diags_Module__loggerFxn8 xdc_runtime_Diags_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__xdc_runtime_Diags_Module__startupDoneFxn)(void);
extern  const CT__xdc_runtime_Diags_Module__startupDoneFxn xdc_runtime_Diags_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__xdc_runtime_Diags_Object__count;
extern  const CT__xdc_runtime_Diags_Object__count xdc_runtime_Diags_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__xdc_runtime_Diags_Object__heap;
extern  const CT__xdc_runtime_Diags_Object__heap xdc_runtime_Diags_Object__heap__C;

 
typedef xdc_SizeT CT__xdc_runtime_Diags_Object__sizeof;
extern  const CT__xdc_runtime_Diags_Object__sizeof xdc_runtime_Diags_Object__sizeof__C;

 
typedef xdc_Ptr CT__xdc_runtime_Diags_Object__table;
extern  const CT__xdc_runtime_Diags_Object__table xdc_runtime_Diags_Object__table__C;

 
typedef xdc_Bool CT__xdc_runtime_Diags_setMaskEnabled;
extern  const CT__xdc_runtime_Diags_setMaskEnabled xdc_runtime_Diags_setMaskEnabled__C;

 
typedef xdc_runtime_Diags_DictElem *CT__xdc_runtime_Diags_dictBase;
extern  const CT__xdc_runtime_Diags_dictBase xdc_runtime_Diags_dictBase__C;




 

 

 

extern xdc_Bool xdc_runtime_Diags_Module__startupDone__S( void );

 

extern void xdc_runtime_Diags_setMask__E( xdc_CString control );




 

 

 

 

 
static inline CT__xdc_runtime_Diags_Module__id xdc_runtime_Diags_Module_id( void ) 
{
    return xdc_runtime_Diags_Module__id__C;
}

 
static inline xdc_Bool xdc_runtime_Diags_Module_hasMask( void ) 
{
    return xdc_runtime_Diags_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 xdc_runtime_Diags_Module_getMask( void ) 
{
    return xdc_runtime_Diags_Module__diagsMask__C != 0 ? *xdc_runtime_Diags_Module__diagsMask__C : 0;
}

 
static inline void xdc_runtime_Diags_Module_setMask( xdc_Bits16 mask ) 
{
    if (xdc_runtime_Diags_Module__diagsMask__C != 0) *xdc_runtime_Diags_Module__diagsMask__C = mask;
}




 












 
















 





 






 


 







 




 



 






 















 




 








 







 















 




 





 




 



 












 





 















 




 





 





 



 






 















 




 





 




 



 






 















 




 





 




 



 

















 
typedef xdc_Bits32 xdc_runtime_Log_Event;


















 
 




 







 













 




 





 



 






 















 




 





 




 



 






 















 




 





 




 



 






 















 




 








 







 















 




 





 




 



 







 













 




 





 



 






 















 




 





 




 



 





 

 
typedef xdc_runtime_Types_CordAddr xdc_runtime_Text_CordAddr;

 
typedef xdc_runtime_Types_Label xdc_runtime_Text_Label;

 
typedef xdc_runtime_Types_RopeId xdc_runtime_Text_RopeId;




 

 
struct xdc_runtime_Text_Node {
    xdc_runtime_Types_RopeId left;
    xdc_runtime_Types_RopeId right;
};

 
typedef xdc_Bool (*xdc_runtime_Text_RopeVisitor)(xdc_Ptr, xdc_String);

 
struct xdc_runtime_Text_MatchVisState {
    xdc_CString pat;
    xdc_Int *lenp;
    xdc_Int res;
};

 
struct xdc_runtime_Text_PrintVisState {
    xdc_Char **bufp;
    xdc_Int len;
    xdc_Int res;
};

 
typedef void (*xdc_runtime_Text_VisitRopeFxn)(xdc_runtime_Text_RopeId, xdc_Fxn, xdc_Ptr);

 
typedef void (*xdc_runtime_Text_VisitRopeFxn2)(xdc_runtime_Text_RopeId, xdc_Fxn, xdc_Ptr, xdc_String[]);




 

 
typedef xdc_Bits32 CT__xdc_runtime_Text_Module__diagsEnabled;
extern  const CT__xdc_runtime_Text_Module__diagsEnabled xdc_runtime_Text_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__xdc_runtime_Text_Module__diagsIncluded;
extern  const CT__xdc_runtime_Text_Module__diagsIncluded xdc_runtime_Text_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__xdc_runtime_Text_Module__diagsMask;
extern  const CT__xdc_runtime_Text_Module__diagsMask xdc_runtime_Text_Module__diagsMask__C;

 
typedef xdc_Ptr CT__xdc_runtime_Text_Module__gateObj;
extern  const CT__xdc_runtime_Text_Module__gateObj xdc_runtime_Text_Module__gateObj__C;

 
typedef xdc_Ptr CT__xdc_runtime_Text_Module__gatePrms;
extern  const CT__xdc_runtime_Text_Module__gatePrms xdc_runtime_Text_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__xdc_runtime_Text_Module__id;
extern  const CT__xdc_runtime_Text_Module__id xdc_runtime_Text_Module__id__C;

 
typedef xdc_Bool CT__xdc_runtime_Text_Module__loggerDefined;
extern  const CT__xdc_runtime_Text_Module__loggerDefined xdc_runtime_Text_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__xdc_runtime_Text_Module__loggerObj;
extern  const CT__xdc_runtime_Text_Module__loggerObj xdc_runtime_Text_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__xdc_runtime_Text_Module__loggerFxn0;
extern  const CT__xdc_runtime_Text_Module__loggerFxn0 xdc_runtime_Text_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__xdc_runtime_Text_Module__loggerFxn1;
extern  const CT__xdc_runtime_Text_Module__loggerFxn1 xdc_runtime_Text_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__xdc_runtime_Text_Module__loggerFxn2;
extern  const CT__xdc_runtime_Text_Module__loggerFxn2 xdc_runtime_Text_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__xdc_runtime_Text_Module__loggerFxn4;
extern  const CT__xdc_runtime_Text_Module__loggerFxn4 xdc_runtime_Text_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__xdc_runtime_Text_Module__loggerFxn8;
extern  const CT__xdc_runtime_Text_Module__loggerFxn8 xdc_runtime_Text_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__xdc_runtime_Text_Module__startupDoneFxn)(void);
extern  const CT__xdc_runtime_Text_Module__startupDoneFxn xdc_runtime_Text_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__xdc_runtime_Text_Object__count;
extern  const CT__xdc_runtime_Text_Object__count xdc_runtime_Text_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__xdc_runtime_Text_Object__heap;
extern  const CT__xdc_runtime_Text_Object__heap xdc_runtime_Text_Object__heap__C;

 
typedef xdc_SizeT CT__xdc_runtime_Text_Object__sizeof;
extern  const CT__xdc_runtime_Text_Object__sizeof xdc_runtime_Text_Object__sizeof__C;

 
typedef xdc_Ptr CT__xdc_runtime_Text_Object__table;
extern  const CT__xdc_runtime_Text_Object__table xdc_runtime_Text_Object__table__C;

 
typedef xdc_String CT__xdc_runtime_Text_nameUnknown;
extern  const CT__xdc_runtime_Text_nameUnknown xdc_runtime_Text_nameUnknown__C;

 
typedef xdc_String CT__xdc_runtime_Text_nameEmpty;
extern  const CT__xdc_runtime_Text_nameEmpty xdc_runtime_Text_nameEmpty__C;

 
typedef xdc_String CT__xdc_runtime_Text_nameStatic;
extern  const CT__xdc_runtime_Text_nameStatic xdc_runtime_Text_nameStatic__C;

 
typedef xdc_Bool CT__xdc_runtime_Text_isLoaded;
extern  const CT__xdc_runtime_Text_isLoaded xdc_runtime_Text_isLoaded__C;

 
typedef xdc_Char __T1_xdc_runtime_Text_charTab;
typedef xdc_Char *__ARRAY1_xdc_runtime_Text_charTab;
typedef __ARRAY1_xdc_runtime_Text_charTab __TA_xdc_runtime_Text_charTab;
typedef __TA_xdc_runtime_Text_charTab CT__xdc_runtime_Text_charTab;
extern  const CT__xdc_runtime_Text_charTab xdc_runtime_Text_charTab__C;

 
typedef xdc_runtime_Text_Node __T1_xdc_runtime_Text_nodeTab;
typedef xdc_runtime_Text_Node *__ARRAY1_xdc_runtime_Text_nodeTab;
typedef __ARRAY1_xdc_runtime_Text_nodeTab __TA_xdc_runtime_Text_nodeTab;
typedef __TA_xdc_runtime_Text_nodeTab CT__xdc_runtime_Text_nodeTab;
extern  const CT__xdc_runtime_Text_nodeTab xdc_runtime_Text_nodeTab__C;

 
typedef xdc_Int16 CT__xdc_runtime_Text_charCnt;
extern  const CT__xdc_runtime_Text_charCnt xdc_runtime_Text_charCnt__C;

 
typedef xdc_Int16 CT__xdc_runtime_Text_nodeCnt;
extern  const CT__xdc_runtime_Text_nodeCnt xdc_runtime_Text_nodeCnt__C;

 
typedef xdc_UInt16 CT__xdc_runtime_Text_unnamedModsLastId;
extern  const CT__xdc_runtime_Text_unnamedModsLastId xdc_runtime_Text_unnamedModsLastId__C;

 
typedef xdc_UInt16 CT__xdc_runtime_Text_registryModsLastId;
extern  const CT__xdc_runtime_Text_registryModsLastId xdc_runtime_Text_registryModsLastId__C;

 
typedef xdc_runtime_Text_VisitRopeFxn CT__xdc_runtime_Text_visitRopeFxn;
extern  const CT__xdc_runtime_Text_visitRopeFxn xdc_runtime_Text_visitRopeFxn__C;

 
typedef xdc_runtime_Text_VisitRopeFxn2 CT__xdc_runtime_Text_visitRopeFxn2;
extern  const CT__xdc_runtime_Text_visitRopeFxn2 xdc_runtime_Text_visitRopeFxn2__C;




 

 

 

extern xdc_Bool xdc_runtime_Text_Module__startupDone__S( void );

 

extern xdc_String xdc_runtime_Text_cordText__E( xdc_runtime_Text_CordAddr cord );

 

extern xdc_String xdc_runtime_Text_ropeText__E( xdc_runtime_Text_RopeId rope );

 

extern xdc_Int xdc_runtime_Text_matchRope__E( xdc_runtime_Text_RopeId rope, xdc_CString pat, xdc_Int *lenp );

 

extern xdc_Int xdc_runtime_Text_putLab__E( xdc_runtime_Types_Label *lab, xdc_Char **bufp, xdc_Int len );

 

extern xdc_Int xdc_runtime_Text_putMod__E( xdc_runtime_Types_ModuleId mid, xdc_Char **bufp, xdc_Int len );

 

extern xdc_Int xdc_runtime_Text_putSite__E( xdc_runtime_Types_Site *site, xdc_Char **bufp, xdc_Int len );

 

extern xdc_Bool xdc_runtime_Text_matchVisFxn__I( xdc_Ptr p, xdc_CString s );

 

extern xdc_Bool xdc_runtime_Text_printVisFxn__I( xdc_Ptr p, xdc_CString s );

 

extern void xdc_runtime_Text_visitRope__I( xdc_runtime_Text_RopeId rope, xdc_Fxn visFxn, xdc_Ptr visState );

 

extern void xdc_runtime_Text_visitRope2__I( xdc_runtime_Text_RopeId rope, xdc_Fxn visFxn, xdc_Ptr visState, xdc_String stack[] );

 

extern xdc_Int xdc_runtime_Text_xprintf__I( xdc_Char **bufp, xdc_SizeT len, xdc_CString fmt, ... );




 

 

 

 

 
static inline CT__xdc_runtime_Text_Module__id xdc_runtime_Text_Module_id( void ) 
{
    return xdc_runtime_Text_Module__id__C;
}

 
static inline xdc_Bool xdc_runtime_Text_Module_hasMask( void ) 
{
    return xdc_runtime_Text_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 xdc_runtime_Text_Module_getMask( void ) 
{
    return xdc_runtime_Text_Module__diagsMask__C != 0 ? *xdc_runtime_Text_Module__diagsMask__C : 0;
}

 
static inline void xdc_runtime_Text_Module_setMask( xdc_Bits16 mask ) 
{
    if (xdc_runtime_Text_Module__diagsMask__C != 0) *xdc_runtime_Text_Module__diagsMask__C = mask;
}




 






 





 



 





 

 

 

 
typedef xdc_IArg __T1_xdc_runtime_Log_EventRec__arg;
typedef xdc_IArg __ARRAY1_xdc_runtime_Log_EventRec__arg[8];
typedef __ARRAY1_xdc_runtime_Log_EventRec__arg __TA_xdc_runtime_Log_EventRec__arg;
struct xdc_runtime_Log_EventRec {
    xdc_runtime_Types_Timestamp64 tstamp;
    xdc_Bits32 serial;
    xdc_runtime_Types_Event evt;
    __TA_xdc_runtime_Log_EventRec__arg arg;
};

 

 
typedef xdc_runtime_Types_RopeId xdc_runtime_Log_EventId;




 




 

 
typedef xdc_Bits32 CT__xdc_runtime_Log_Module__diagsEnabled;
extern  const CT__xdc_runtime_Log_Module__diagsEnabled xdc_runtime_Log_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__xdc_runtime_Log_Module__diagsIncluded;
extern  const CT__xdc_runtime_Log_Module__diagsIncluded xdc_runtime_Log_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__xdc_runtime_Log_Module__diagsMask;
extern  const CT__xdc_runtime_Log_Module__diagsMask xdc_runtime_Log_Module__diagsMask__C;

 
typedef xdc_Ptr CT__xdc_runtime_Log_Module__gateObj;
extern  const CT__xdc_runtime_Log_Module__gateObj xdc_runtime_Log_Module__gateObj__C;

 
typedef xdc_Ptr CT__xdc_runtime_Log_Module__gatePrms;
extern  const CT__xdc_runtime_Log_Module__gatePrms xdc_runtime_Log_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__xdc_runtime_Log_Module__id;
extern  const CT__xdc_runtime_Log_Module__id xdc_runtime_Log_Module__id__C;

 
typedef xdc_Bool CT__xdc_runtime_Log_Module__loggerDefined;
extern  const CT__xdc_runtime_Log_Module__loggerDefined xdc_runtime_Log_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__xdc_runtime_Log_Module__loggerObj;
extern  const CT__xdc_runtime_Log_Module__loggerObj xdc_runtime_Log_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__xdc_runtime_Log_Module__loggerFxn0;
extern  const CT__xdc_runtime_Log_Module__loggerFxn0 xdc_runtime_Log_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__xdc_runtime_Log_Module__loggerFxn1;
extern  const CT__xdc_runtime_Log_Module__loggerFxn1 xdc_runtime_Log_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__xdc_runtime_Log_Module__loggerFxn2;
extern  const CT__xdc_runtime_Log_Module__loggerFxn2 xdc_runtime_Log_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__xdc_runtime_Log_Module__loggerFxn4;
extern  const CT__xdc_runtime_Log_Module__loggerFxn4 xdc_runtime_Log_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__xdc_runtime_Log_Module__loggerFxn8;
extern  const CT__xdc_runtime_Log_Module__loggerFxn8 xdc_runtime_Log_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__xdc_runtime_Log_Module__startupDoneFxn)(void);
extern  const CT__xdc_runtime_Log_Module__startupDoneFxn xdc_runtime_Log_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__xdc_runtime_Log_Object__count;
extern  const CT__xdc_runtime_Log_Object__count xdc_runtime_Log_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__xdc_runtime_Log_Object__heap;
extern  const CT__xdc_runtime_Log_Object__heap xdc_runtime_Log_Object__heap__C;

 
typedef xdc_SizeT CT__xdc_runtime_Log_Object__sizeof;
extern  const CT__xdc_runtime_Log_Object__sizeof xdc_runtime_Log_Object__sizeof__C;

 
typedef xdc_Ptr CT__xdc_runtime_Log_Object__table;
extern  const CT__xdc_runtime_Log_Object__table xdc_runtime_Log_Object__table__C;

 
typedef xdc_runtime_Log_Event CT__xdc_runtime_Log_L_construct;
extern  const CT__xdc_runtime_Log_L_construct xdc_runtime_Log_L_construct__C;

 
typedef xdc_runtime_Log_Event CT__xdc_runtime_Log_L_create;
extern  const CT__xdc_runtime_Log_L_create xdc_runtime_Log_L_create__C;

 
typedef xdc_runtime_Log_Event CT__xdc_runtime_Log_L_destruct;
extern  const CT__xdc_runtime_Log_L_destruct xdc_runtime_Log_L_destruct__C;

 
typedef xdc_runtime_Log_Event CT__xdc_runtime_Log_L_delete;
extern  const CT__xdc_runtime_Log_L_delete xdc_runtime_Log_L_delete__C;

 
typedef xdc_runtime_Log_Event CT__xdc_runtime_Log_L_error;
extern  const CT__xdc_runtime_Log_L_error xdc_runtime_Log_L_error__C;

 
typedef xdc_runtime_Log_Event CT__xdc_runtime_Log_L_warning;
extern  const CT__xdc_runtime_Log_L_warning xdc_runtime_Log_L_warning__C;

 
typedef xdc_runtime_Log_Event CT__xdc_runtime_Log_L_info;
extern  const CT__xdc_runtime_Log_L_info xdc_runtime_Log_L_info__C;

 
typedef xdc_runtime_Log_Event CT__xdc_runtime_Log_L_start;
extern  const CT__xdc_runtime_Log_L_start xdc_runtime_Log_L_start__C;

 
typedef xdc_runtime_Log_Event CT__xdc_runtime_Log_L_stop;
extern  const CT__xdc_runtime_Log_L_stop xdc_runtime_Log_L_stop__C;

 
typedef xdc_runtime_Log_Event CT__xdc_runtime_Log_L_startInstance;
extern  const CT__xdc_runtime_Log_L_startInstance xdc_runtime_Log_L_startInstance__C;

 
typedef xdc_runtime_Log_Event CT__xdc_runtime_Log_L_stopInstance;
extern  const CT__xdc_runtime_Log_L_stopInstance xdc_runtime_Log_L_stopInstance__C;




 

 

 

extern xdc_Bool xdc_runtime_Log_Module__startupDone__S( void );

 

extern void xdc_runtime_Log_doPrint__E( xdc_runtime_Log_EventRec *evRec );




 

 

 

 

 
static inline CT__xdc_runtime_Log_Module__id xdc_runtime_Log_Module_id( void ) 
{
    return xdc_runtime_Log_Module__id__C;
}

 
static inline xdc_Bool xdc_runtime_Log_Module_hasMask( void ) 
{
    return xdc_runtime_Log_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 xdc_runtime_Log_Module_getMask( void ) 
{
    return xdc_runtime_Log_Module__diagsMask__C != 0 ? *xdc_runtime_Log_Module__diagsMask__C : 0;
}

 
static inline void xdc_runtime_Log_Module_setMask( xdc_Bits16 mask ) 
{
    if (xdc_runtime_Log_Module__diagsMask__C != 0) *xdc_runtime_Log_Module__diagsMask__C = mask;
}




 












 

















 






















 




 



 



 



 











 




   



 











 

    
     
    







 
 
    
    
    


    
    

    
  















 
 
    
    








 
 
        
    
    

    



 
 
    
    
    

    



 

    
    
    



 







 




 



 






 















 




 








 







 















 




 





 




 



 












 


 





 















 




 





 




 



 






 















 




 





 




 



 




 
typedef xdc_Bits32 xdc_runtime_Assert_Id;











 
 



 







 













 




 





 



 






 















 




 





 




 



 






 















 




 





 





 



 






 















 




 





 




 



 





 

 




 




 

 
typedef xdc_Bits32 CT__xdc_runtime_Assert_Module__diagsEnabled;
extern  const CT__xdc_runtime_Assert_Module__diagsEnabled xdc_runtime_Assert_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__xdc_runtime_Assert_Module__diagsIncluded;
extern  const CT__xdc_runtime_Assert_Module__diagsIncluded xdc_runtime_Assert_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__xdc_runtime_Assert_Module__diagsMask;
extern  const CT__xdc_runtime_Assert_Module__diagsMask xdc_runtime_Assert_Module__diagsMask__C;

 
typedef xdc_Ptr CT__xdc_runtime_Assert_Module__gateObj;
extern  const CT__xdc_runtime_Assert_Module__gateObj xdc_runtime_Assert_Module__gateObj__C;

 
typedef xdc_Ptr CT__xdc_runtime_Assert_Module__gatePrms;
extern  const CT__xdc_runtime_Assert_Module__gatePrms xdc_runtime_Assert_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__xdc_runtime_Assert_Module__id;
extern  const CT__xdc_runtime_Assert_Module__id xdc_runtime_Assert_Module__id__C;

 
typedef xdc_Bool CT__xdc_runtime_Assert_Module__loggerDefined;
extern  const CT__xdc_runtime_Assert_Module__loggerDefined xdc_runtime_Assert_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__xdc_runtime_Assert_Module__loggerObj;
extern  const CT__xdc_runtime_Assert_Module__loggerObj xdc_runtime_Assert_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__xdc_runtime_Assert_Module__loggerFxn0;
extern  const CT__xdc_runtime_Assert_Module__loggerFxn0 xdc_runtime_Assert_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__xdc_runtime_Assert_Module__loggerFxn1;
extern  const CT__xdc_runtime_Assert_Module__loggerFxn1 xdc_runtime_Assert_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__xdc_runtime_Assert_Module__loggerFxn2;
extern  const CT__xdc_runtime_Assert_Module__loggerFxn2 xdc_runtime_Assert_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__xdc_runtime_Assert_Module__loggerFxn4;
extern  const CT__xdc_runtime_Assert_Module__loggerFxn4 xdc_runtime_Assert_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__xdc_runtime_Assert_Module__loggerFxn8;
extern  const CT__xdc_runtime_Assert_Module__loggerFxn8 xdc_runtime_Assert_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__xdc_runtime_Assert_Module__startupDoneFxn)(void);
extern  const CT__xdc_runtime_Assert_Module__startupDoneFxn xdc_runtime_Assert_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__xdc_runtime_Assert_Object__count;
extern  const CT__xdc_runtime_Assert_Object__count xdc_runtime_Assert_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__xdc_runtime_Assert_Object__heap;
extern  const CT__xdc_runtime_Assert_Object__heap xdc_runtime_Assert_Object__heap__C;

 
typedef xdc_SizeT CT__xdc_runtime_Assert_Object__sizeof;
extern  const CT__xdc_runtime_Assert_Object__sizeof xdc_runtime_Assert_Object__sizeof__C;

 
typedef xdc_Ptr CT__xdc_runtime_Assert_Object__table;
extern  const CT__xdc_runtime_Assert_Object__table xdc_runtime_Assert_Object__table__C;

 
typedef xdc_runtime_Error_Id CT__xdc_runtime_Assert_E_assertFailed;
extern  const CT__xdc_runtime_Assert_E_assertFailed xdc_runtime_Assert_E_assertFailed__C;




 

 

 

extern xdc_Bool xdc_runtime_Assert_Module__startupDone__S( void );

 

extern void xdc_runtime_Assert_raise__I( xdc_runtime_Types_ModuleId mod, xdc_CString file, xdc_Int line, xdc_runtime_Assert_Id id );




 

 

 

 

 
static inline CT__xdc_runtime_Assert_Module__id xdc_runtime_Assert_Module_id( void ) 
{
    return xdc_runtime_Assert_Module__id__C;
}

 
static inline xdc_Bool xdc_runtime_Assert_Module_hasMask( void ) 
{
    return xdc_runtime_Assert_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 xdc_runtime_Assert_Module_getMask( void ) 
{
    return xdc_runtime_Assert_Module__diagsMask__C != 0 ? *xdc_runtime_Assert_Module__diagsMask__C : 0;
}

 
static inline void xdc_runtime_Assert_Module_setMask( xdc_Bits16 mask ) 
{
    if (xdc_runtime_Assert_Module__diagsMask__C != 0) *xdc_runtime_Assert_Module__diagsMask__C = mask;
}




 












 




 



 





 







 




 



 






 
















 




 








 







 















 




 





 




 



 






 














 




 





 



 







 













 




 





 



 





 

 
struct ti_sysbios_knl_Queue_Elem {
    ti_sysbios_knl_Queue_Elem *volatile next;
    ti_sysbios_knl_Queue_Elem *volatile prev;
};




 




 

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Queue_Module__diagsEnabled;
extern  const CT__ti_sysbios_knl_Queue_Module__diagsEnabled ti_sysbios_knl_Queue_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Queue_Module__diagsIncluded;
extern  const CT__ti_sysbios_knl_Queue_Module__diagsIncluded ti_sysbios_knl_Queue_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__ti_sysbios_knl_Queue_Module__diagsMask;
extern  const CT__ti_sysbios_knl_Queue_Module__diagsMask ti_sysbios_knl_Queue_Module__diagsMask__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Queue_Module__gateObj;
extern  const CT__ti_sysbios_knl_Queue_Module__gateObj ti_sysbios_knl_Queue_Module__gateObj__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Queue_Module__gatePrms;
extern  const CT__ti_sysbios_knl_Queue_Module__gatePrms ti_sysbios_knl_Queue_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__ti_sysbios_knl_Queue_Module__id;
extern  const CT__ti_sysbios_knl_Queue_Module__id ti_sysbios_knl_Queue_Module__id__C;

 
typedef xdc_Bool CT__ti_sysbios_knl_Queue_Module__loggerDefined;
extern  const CT__ti_sysbios_knl_Queue_Module__loggerDefined ti_sysbios_knl_Queue_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Queue_Module__loggerObj;
extern  const CT__ti_sysbios_knl_Queue_Module__loggerObj ti_sysbios_knl_Queue_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__ti_sysbios_knl_Queue_Module__loggerFxn0;
extern  const CT__ti_sysbios_knl_Queue_Module__loggerFxn0 ti_sysbios_knl_Queue_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__ti_sysbios_knl_Queue_Module__loggerFxn1;
extern  const CT__ti_sysbios_knl_Queue_Module__loggerFxn1 ti_sysbios_knl_Queue_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__ti_sysbios_knl_Queue_Module__loggerFxn2;
extern  const CT__ti_sysbios_knl_Queue_Module__loggerFxn2 ti_sysbios_knl_Queue_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__ti_sysbios_knl_Queue_Module__loggerFxn4;
extern  const CT__ti_sysbios_knl_Queue_Module__loggerFxn4 ti_sysbios_knl_Queue_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__ti_sysbios_knl_Queue_Module__loggerFxn8;
extern  const CT__ti_sysbios_knl_Queue_Module__loggerFxn8 ti_sysbios_knl_Queue_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__ti_sysbios_knl_Queue_Module__startupDoneFxn)(void);
extern  const CT__ti_sysbios_knl_Queue_Module__startupDoneFxn ti_sysbios_knl_Queue_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__ti_sysbios_knl_Queue_Object__count;
extern  const CT__ti_sysbios_knl_Queue_Object__count ti_sysbios_knl_Queue_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__ti_sysbios_knl_Queue_Object__heap;
extern  const CT__ti_sysbios_knl_Queue_Object__heap ti_sysbios_knl_Queue_Object__heap__C;

 
typedef xdc_SizeT CT__ti_sysbios_knl_Queue_Object__sizeof;
extern  const CT__ti_sysbios_knl_Queue_Object__sizeof ti_sysbios_knl_Queue_Object__sizeof__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Queue_Object__table;
extern  const CT__ti_sysbios_knl_Queue_Object__table ti_sysbios_knl_Queue_Object__table__C;




 

 
struct ti_sysbios_knl_Queue_Params {
    size_t __size;
    const void *__self;
    void *__fxns;
    xdc_runtime_IInstance_Params *instance;
    xdc_runtime_IInstance_Params __iprms;
};

 
struct ti_sysbios_knl_Queue_Struct {
    ti_sysbios_knl_Queue_Elem __f0;
    xdc_runtime_Types_CordAddr __name;
};




 

 

 

extern void ti_sysbios_knl_Queue_Instance_init__E(ti_sysbios_knl_Queue_Object *, const ti_sysbios_knl_Queue_Params *);

 

extern xdc_runtime_Types_Label *ti_sysbios_knl_Queue_Handle__label__S( xdc_Ptr obj, xdc_runtime_Types_Label *lab );

 

extern xdc_Bool ti_sysbios_knl_Queue_Module__startupDone__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Queue_Object__create__S( xdc_Ptr __oa, xdc_SizeT __osz, xdc_Ptr __aa, const xdc_UChar *__pa, xdc_SizeT __psz, xdc_runtime_Error_Block *__eb );

 

extern ti_sysbios_knl_Queue_Handle ti_sysbios_knl_Queue_create( const ti_sysbios_knl_Queue_Params *__prms, xdc_runtime_Error_Block *__eb );

 

extern void ti_sysbios_knl_Queue_construct( ti_sysbios_knl_Queue_Struct *__obj, const ti_sysbios_knl_Queue_Params *__prms );

 

extern void ti_sysbios_knl_Queue_Object__delete__S( xdc_Ptr instp );

 

extern void ti_sysbios_knl_Queue_delete(ti_sysbios_knl_Queue_Handle *instp);

 

extern void ti_sysbios_knl_Queue_Object__destruct__S( xdc_Ptr objp );

 

extern void ti_sysbios_knl_Queue_destruct(ti_sysbios_knl_Queue_Struct *obj);

 

extern xdc_Ptr ti_sysbios_knl_Queue_Object__get__S( xdc_Ptr oarr, xdc_Int i );

 

extern xdc_Ptr ti_sysbios_knl_Queue_Object__first__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Queue_Object__next__S( xdc_Ptr obj );

 

extern void ti_sysbios_knl_Queue_Params__init__S( xdc_Ptr dst, const void *src, xdc_SizeT psz, xdc_SizeT isz );

 

extern void ti_sysbios_knl_Queue_elemClear__E( ti_sysbios_knl_Queue_Elem *qelem );

 

extern void ti_sysbios_knl_Queue_insert__E( ti_sysbios_knl_Queue_Elem *qelem, ti_sysbios_knl_Queue_Elem *elem );

 

extern xdc_Ptr ti_sysbios_knl_Queue_next__E( ti_sysbios_knl_Queue_Elem *qelem );

 

extern xdc_Ptr ti_sysbios_knl_Queue_prev__E( ti_sysbios_knl_Queue_Elem *qelem );

 

extern void ti_sysbios_knl_Queue_remove__E( ti_sysbios_knl_Queue_Elem *qelem );

 

extern xdc_Bool ti_sysbios_knl_Queue_isQueued__E( ti_sysbios_knl_Queue_Elem *qelem );

 

extern xdc_Ptr ti_sysbios_knl_Queue_dequeue__E( ti_sysbios_knl_Queue_Handle __inst );

 

extern xdc_Bool ti_sysbios_knl_Queue_empty__E( ti_sysbios_knl_Queue_Handle __inst );

 

extern void ti_sysbios_knl_Queue_enqueue__E( ti_sysbios_knl_Queue_Handle __inst, ti_sysbios_knl_Queue_Elem *elem );

 

extern xdc_Ptr ti_sysbios_knl_Queue_get__E( ti_sysbios_knl_Queue_Handle __inst );

 

extern xdc_Ptr ti_sysbios_knl_Queue_getTail__E( ti_sysbios_knl_Queue_Handle __inst );

 

extern xdc_Ptr ti_sysbios_knl_Queue_head__E( ti_sysbios_knl_Queue_Handle __inst );

 

extern void ti_sysbios_knl_Queue_put__E( ti_sysbios_knl_Queue_Handle __inst, ti_sysbios_knl_Queue_Elem *elem );

 

extern void ti_sysbios_knl_Queue_putHead__E( ti_sysbios_knl_Queue_Handle __inst, ti_sysbios_knl_Queue_Elem *elem );




 

 

 

 

 
static inline CT__ti_sysbios_knl_Queue_Module__id ti_sysbios_knl_Queue_Module_id( void ) 
{
    return ti_sysbios_knl_Queue_Module__id__C;
}

 
static inline xdc_Bool ti_sysbios_knl_Queue_Module_hasMask( void ) 
{
    return ti_sysbios_knl_Queue_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 ti_sysbios_knl_Queue_Module_getMask( void ) 
{
    return ti_sysbios_knl_Queue_Module__diagsMask__C != 0 ? *ti_sysbios_knl_Queue_Module__diagsMask__C : 0;
}

 
static inline void ti_sysbios_knl_Queue_Module_setMask( xdc_Bits16 mask ) 
{
    if (ti_sysbios_knl_Queue_Module__diagsMask__C != 0) *ti_sysbios_knl_Queue_Module__diagsMask__C = mask;
}

 
static inline void ti_sysbios_knl_Queue_Params_init( ti_sysbios_knl_Queue_Params *prms ) 
{
    if (prms) {
        ti_sysbios_knl_Queue_Params__init__S(prms, 0, sizeof(ti_sysbios_knl_Queue_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 
static inline void ti_sysbios_knl_Queue_Params_copy(ti_sysbios_knl_Queue_Params *dst, const ti_sysbios_knl_Queue_Params *src) 
{
    if (dst) {
        ti_sysbios_knl_Queue_Params__init__S(dst, (const void *)src, sizeof(ti_sysbios_knl_Queue_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 

 

 
static inline ti_sysbios_knl_Queue_Handle ti_sysbios_knl_Queue_Object_get(ti_sysbios_knl_Queue_Instance_State *oarr, int i) 
{
    return (ti_sysbios_knl_Queue_Handle)ti_sysbios_knl_Queue_Object__get__S(oarr, i);
}

 
static inline ti_sysbios_knl_Queue_Handle ti_sysbios_knl_Queue_Object_first( void )
{
    return (ti_sysbios_knl_Queue_Handle)ti_sysbios_knl_Queue_Object__first__S();
}

 
static inline ti_sysbios_knl_Queue_Handle ti_sysbios_knl_Queue_Object_next( ti_sysbios_knl_Queue_Object *obj )
{
    return (ti_sysbios_knl_Queue_Handle)ti_sysbios_knl_Queue_Object__next__S(obj);
}

 
static inline xdc_runtime_Types_Label *ti_sysbios_knl_Queue_Handle_label( ti_sysbios_knl_Queue_Handle inst, xdc_runtime_Types_Label *lab )
{
    return ti_sysbios_knl_Queue_Handle__label__S(inst, lab);
}

 
static inline xdc_String ti_sysbios_knl_Queue_Handle_name( ti_sysbios_knl_Queue_Handle inst )
{
    xdc_runtime_Types_Label lab;
    return ti_sysbios_knl_Queue_Handle__label__S(inst, &lab)->iname;
}

 
static inline ti_sysbios_knl_Queue_Handle ti_sysbios_knl_Queue_handle( ti_sysbios_knl_Queue_Struct *str )
{
    return (ti_sysbios_knl_Queue_Handle)str;
}

 
static inline ti_sysbios_knl_Queue_Struct *ti_sysbios_knl_Queue_struct( ti_sysbios_knl_Queue_Handle inst )
{
    return (ti_sysbios_knl_Queue_Struct*)inst;
}




 






 





 






 

















 




 








 







 















 




 





 




 



 






 














 




 





 



 































 







 















 




 





 





 



 






 















 




 





 




 



 






 















 




 





 




 



 






 















 




 





 




 



 






 














 




 





 



 






 
















 




 





 





 






 













 




 





 



 






 













 




 








 







 















 




 





 




 



 






 





 

typedef struct ti_sysbios_interfaces_ICore_Fxns__ ti_sysbios_interfaces_ICore_Fxns__;
typedef const ti_sysbios_interfaces_ICore_Fxns__* ti_sysbios_interfaces_ICore_Module;



 

typedef struct ti_sysbios_interfaces_IHwi_HookSet ti_sysbios_interfaces_IHwi_HookSet;
typedef struct ti_sysbios_interfaces_IHwi_StackInfo ti_sysbios_interfaces_IHwi_StackInfo;
typedef struct ti_sysbios_interfaces_IHwi_Fxns__ ti_sysbios_interfaces_IHwi_Fxns__;
typedef const ti_sysbios_interfaces_IHwi_Fxns__* ti_sysbios_interfaces_IHwi_Module;
typedef struct ti_sysbios_interfaces_IHwi_Params ti_sysbios_interfaces_IHwi_Params;
typedef struct ti_sysbios_interfaces_IHwi___Object { ti_sysbios_interfaces_IHwi_Fxns__* __fxns; xdc_Bits32 __label; } *ti_sysbios_interfaces_IHwi_Handle;



 

typedef struct ti_sysbios_interfaces_ITaskSupport_Fxns__ ti_sysbios_interfaces_ITaskSupport_Fxns__;
typedef const ti_sysbios_interfaces_ITaskSupport_Fxns__* ti_sysbios_interfaces_ITaskSupport_Module;



 

typedef struct ti_sysbios_interfaces_ITimer_Fxns__ ti_sysbios_interfaces_ITimer_Fxns__;
typedef const ti_sysbios_interfaces_ITimer_Fxns__* ti_sysbios_interfaces_ITimer_Module;
typedef struct ti_sysbios_interfaces_ITimer_Params ti_sysbios_interfaces_ITimer_Params;
typedef struct ti_sysbios_interfaces_ITimer___Object { ti_sysbios_interfaces_ITimer_Fxns__* __fxns; xdc_Bits32 __label; } *ti_sysbios_interfaces_ITimer_Handle;



 

typedef struct ti_sysbios_interfaces_ITimerSupport_Fxns__ ti_sysbios_interfaces_ITimerSupport_Fxns__;
typedef const ti_sysbios_interfaces_ITimerSupport_Fxns__* ti_sysbios_interfaces_ITimerSupport_Module;



 

typedef struct ti_sysbios_interfaces_ITimestamp_Fxns__ ti_sysbios_interfaces_ITimestamp_Fxns__;
typedef const ti_sysbios_interfaces_ITimestamp_Fxns__* ti_sysbios_interfaces_ITimestamp_Module;



 

typedef struct ti_sysbios_interfaces_IIntrinsicsSupport_Fxns__ ti_sysbios_interfaces_IIntrinsicsSupport_Fxns__;
typedef const ti_sysbios_interfaces_IIntrinsicsSupport_Fxns__* ti_sysbios_interfaces_IIntrinsicsSupport_Module;



 

typedef struct ti_sysbios_interfaces_ICache_Fxns__ ti_sysbios_interfaces_ICache_Fxns__;
typedef const ti_sysbios_interfaces_ICache_Fxns__* ti_sysbios_interfaces_ICache_Module;



 

typedef struct ti_sysbios_interfaces_IPower_Fxns__ ti_sysbios_interfaces_IPower_Fxns__;
typedef const ti_sysbios_interfaces_IPower_Fxns__* ti_sysbios_interfaces_IPower_Module;



 

typedef struct ti_sysbios_interfaces_IRomDevice_Fxns__ ti_sysbios_interfaces_IRomDevice_Fxns__;
typedef const ti_sysbios_interfaces_IRomDevice_Fxns__* ti_sysbios_interfaces_IRomDevice_Module;



 

typedef struct ti_sysbios_interfaces_ISeconds_Time ti_sysbios_interfaces_ISeconds_Time;
typedef struct ti_sysbios_interfaces_ISeconds_Fxns__ ti_sysbios_interfaces_ISeconds_Fxns__;
typedef const ti_sysbios_interfaces_ISeconds_Fxns__* ti_sysbios_interfaces_ISeconds_Module;








 















 




 





 





 



 






 













 




 





 



 





 

 
typedef void (*ti_sysbios_interfaces_ITaskSupport_FuncPtr)(void);




 

 
struct ti_sysbios_interfaces_ITaskSupport_Fxns__ {
    xdc_runtime_Types_Base* __base;
    const xdc_runtime_Types_SysFxns2 *__sysp;
    xdc_Ptr (*start)(xdc_Ptr, ti_sysbios_interfaces_ITaskSupport_FuncPtr, ti_sysbios_interfaces_ITaskSupport_FuncPtr, xdc_runtime_Error_Block*);
    void (*swap)(xdc_Ptr*, xdc_Ptr*);
    xdc_Bool (*checkStack)(xdc_Char*, xdc_SizeT);
    xdc_SizeT (*stackUsed)(xdc_Char*, xdc_SizeT);
    xdc_UInt (*getStackAlignment)(void);
    xdc_SizeT (*getDefaultStackSize)(void);
    xdc_runtime_Types_SysFxns2 __sfxns;
};

 
extern const xdc_runtime_Types_Base ti_sysbios_interfaces_ITaskSupport_Interface__BASE__C;




 

 
static inline xdc_runtime_Types_ModuleId ti_sysbios_interfaces_ITaskSupport_Module_id( ti_sysbios_interfaces_ITaskSupport_Module mod )
{
    return mod->__sysp->__mid;
}

 
static inline xdc_Ptr ti_sysbios_interfaces_ITaskSupport_start( ti_sysbios_interfaces_ITaskSupport_Module __inst, xdc_Ptr curTask, ti_sysbios_interfaces_ITaskSupport_FuncPtr enter, ti_sysbios_interfaces_ITaskSupport_FuncPtr exit, xdc_runtime_Error_Block *eb )
{
    return __inst->start(curTask, enter, exit, eb);
}

 
static inline void ti_sysbios_interfaces_ITaskSupport_swap( ti_sysbios_interfaces_ITaskSupport_Module __inst, xdc_Ptr *oldtskContext, xdc_Ptr *newtskContext )
{
    __inst->swap(oldtskContext, newtskContext);
}

 
static inline xdc_Bool ti_sysbios_interfaces_ITaskSupport_checkStack( ti_sysbios_interfaces_ITaskSupport_Module __inst, xdc_Char *stack, xdc_SizeT size )
{
    return __inst->checkStack(stack, size);
}

 
static inline xdc_SizeT ti_sysbios_interfaces_ITaskSupport_stackUsed( ti_sysbios_interfaces_ITaskSupport_Module __inst, xdc_Char *stack, xdc_SizeT size )
{
    return __inst->stackUsed(stack, size);
}

 
static inline xdc_UInt ti_sysbios_interfaces_ITaskSupport_getStackAlignment( ti_sysbios_interfaces_ITaskSupport_Module __inst )
{
    return __inst->getStackAlignment();
}

 
static inline xdc_SizeT ti_sysbios_interfaces_ITaskSupport_getDefaultStackSize( ti_sysbios_interfaces_ITaskSupport_Module __inst )
{
    return __inst->getDefaultStackSize();
}




 






 

 
typedef xdc_Ptr (*ti_sysbios_interfaces_ITaskSupport_start_FxnT)(xdc_Ptr, ti_sysbios_interfaces_ITaskSupport_FuncPtr, ti_sysbios_interfaces_ITaskSupport_FuncPtr, xdc_runtime_Error_Block*);
static inline ti_sysbios_interfaces_ITaskSupport_start_FxnT ti_sysbios_interfaces_ITaskSupport_start_fxnP( ti_sysbios_interfaces_ITaskSupport_Module __inst )
{
    return (ti_sysbios_interfaces_ITaskSupport_start_FxnT)__inst->start;
}

 
typedef void (*ti_sysbios_interfaces_ITaskSupport_swap_FxnT)(xdc_Ptr*, xdc_Ptr*);
static inline ti_sysbios_interfaces_ITaskSupport_swap_FxnT ti_sysbios_interfaces_ITaskSupport_swap_fxnP( ti_sysbios_interfaces_ITaskSupport_Module __inst )
{
    return (ti_sysbios_interfaces_ITaskSupport_swap_FxnT)__inst->swap;
}

 
typedef xdc_Bool (*ti_sysbios_interfaces_ITaskSupport_checkStack_FxnT)(xdc_Char*, xdc_SizeT);
static inline ti_sysbios_interfaces_ITaskSupport_checkStack_FxnT ti_sysbios_interfaces_ITaskSupport_checkStack_fxnP( ti_sysbios_interfaces_ITaskSupport_Module __inst )
{
    return (ti_sysbios_interfaces_ITaskSupport_checkStack_FxnT)__inst->checkStack;
}

 
typedef xdc_SizeT (*ti_sysbios_interfaces_ITaskSupport_stackUsed_FxnT)(xdc_Char*, xdc_SizeT);
static inline ti_sysbios_interfaces_ITaskSupport_stackUsed_FxnT ti_sysbios_interfaces_ITaskSupport_stackUsed_fxnP( ti_sysbios_interfaces_ITaskSupport_Module __inst )
{
    return (ti_sysbios_interfaces_ITaskSupport_stackUsed_FxnT)__inst->stackUsed;
}

 
typedef xdc_UInt (*ti_sysbios_interfaces_ITaskSupport_getStackAlignment_FxnT)(void);
static inline ti_sysbios_interfaces_ITaskSupport_getStackAlignment_FxnT ti_sysbios_interfaces_ITaskSupport_getStackAlignment_fxnP( ti_sysbios_interfaces_ITaskSupport_Module __inst )
{
    return (ti_sysbios_interfaces_ITaskSupport_getStackAlignment_FxnT)__inst->getStackAlignment;
}

 
typedef xdc_SizeT (*ti_sysbios_interfaces_ITaskSupport_getDefaultStackSize_FxnT)(void);
static inline ti_sysbios_interfaces_ITaskSupport_getDefaultStackSize_FxnT ti_sysbios_interfaces_ITaskSupport_getDefaultStackSize_fxnP( ti_sysbios_interfaces_ITaskSupport_Module __inst )
{
    return (ti_sysbios_interfaces_ITaskSupport_getDefaultStackSize_FxnT)__inst->getDefaultStackSize;
}




 






 






 

















 




 








 







 















 




 





 




 



 






 














 




 





 



 







 















 




 





 




 



 






 















 




 





 




 



 






 















 




 





 




 



 






 













 




 





 



 






 















 




 








 







 















 




 





 




 



 






 














 




 





 



 







 















 




 





 




 



 






 













 




 





 



 





 

 
typedef void (*ti_sysbios_interfaces_ITimer_FuncPtr)(xdc_UArg);

 

 
enum ti_sysbios_interfaces_ITimer_StartMode {
    ti_sysbios_interfaces_ITimer_StartMode_AUTO,
    ti_sysbios_interfaces_ITimer_StartMode_USER
};
typedef enum ti_sysbios_interfaces_ITimer_StartMode ti_sysbios_interfaces_ITimer_StartMode;

 
enum ti_sysbios_interfaces_ITimer_RunMode {
    ti_sysbios_interfaces_ITimer_RunMode_CONTINUOUS,
    ti_sysbios_interfaces_ITimer_RunMode_ONESHOT,
    ti_sysbios_interfaces_ITimer_RunMode_DYNAMIC
};
typedef enum ti_sysbios_interfaces_ITimer_RunMode ti_sysbios_interfaces_ITimer_RunMode;

 
enum ti_sysbios_interfaces_ITimer_Status {
    ti_sysbios_interfaces_ITimer_Status_INUSE,
    ti_sysbios_interfaces_ITimer_Status_FREE
};
typedef enum ti_sysbios_interfaces_ITimer_Status ti_sysbios_interfaces_ITimer_Status;

 
enum ti_sysbios_interfaces_ITimer_PeriodType {
    ti_sysbios_interfaces_ITimer_PeriodType_MICROSECS,
    ti_sysbios_interfaces_ITimer_PeriodType_COUNTS
};
typedef enum ti_sysbios_interfaces_ITimer_PeriodType ti_sysbios_interfaces_ITimer_PeriodType;




 

 
typedef struct ti_sysbios_interfaces_ITimer_Args__create {
    xdc_Int id;
    ti_sysbios_interfaces_ITimer_FuncPtr tickFxn;
} ti_sysbios_interfaces_ITimer_Args__create;




 

 
struct ti_sysbios_interfaces_ITimer_Params {
    size_t __size;
    const void *__self;
    void *__fxns;
    xdc_runtime_IInstance_Params *instance;
    ti_sysbios_interfaces_ITimer_RunMode runMode;
    ti_sysbios_interfaces_ITimer_StartMode startMode;
    xdc_UArg arg;
    xdc_UInt32 period;
    ti_sysbios_interfaces_ITimer_PeriodType periodType;
    xdc_runtime_Types_FreqHz extFreq;
};




 

 
struct ti_sysbios_interfaces_ITimer_Fxns__ {
    xdc_runtime_Types_Base* __base;
    const xdc_runtime_Types_SysFxns2 *__sysp;
    xdc_UInt (*getNumTimers)(void);
    ti_sysbios_interfaces_ITimer_Status (*getStatus)(xdc_UInt);
    void (*startup)(void);
    xdc_UInt32 (*getMaxTicks)(void*);
    void (*setNextTick)(void*, xdc_UInt32);
    void (*start)(void*);
    void (*stop)(void*);
    void (*setPeriod)(void*, xdc_UInt32);
    xdc_Bool (*setPeriodMicroSecs)(void*, xdc_UInt32);
    xdc_UInt32 (*getPeriod)(void*);
    xdc_UInt32 (*getCount)(void*);
    void (*getFreq)(void*, xdc_runtime_Types_FreqHz*);
    ti_sysbios_interfaces_ITimer_FuncPtr (*getFunc)(void*, xdc_UArg*);
    void (*setFunc)(void*, ti_sysbios_interfaces_ITimer_FuncPtr, xdc_UArg);
    void (*trigger)(void*, xdc_UInt32);
    xdc_UInt32 (*getExpiredCounts)(void*);
    xdc_UInt32 (*getExpiredTicks)(void*, xdc_UInt32);
    xdc_UInt32 (*getCurrentTick)(void*, xdc_Bool);
    xdc_runtime_Types_SysFxns2 __sfxns;
};

 
extern const xdc_runtime_Types_Base ti_sysbios_interfaces_ITimer_Interface__BASE__C;




 

 

extern ti_sysbios_interfaces_ITimer_Handle ti_sysbios_interfaces_ITimer_create(ti_sysbios_interfaces_ITimer_Module, xdc_Int id, ti_sysbios_interfaces_ITimer_FuncPtr tickFxn, const ti_sysbios_interfaces_ITimer_Params *, xdc_runtime_Error_Block *__eb);

 

extern void ti_sysbios_interfaces_ITimer_delete(ti_sysbios_interfaces_ITimer_Handle *);

 
static inline ti_sysbios_interfaces_ITimer_Module ti_sysbios_interfaces_ITimer_Handle_to_Module( ti_sysbios_interfaces_ITimer_Handle inst )
{
    return inst->__fxns;
}

 
static inline xdc_runtime_Types_Label *ti_sysbios_interfaces_ITimer_Handle_label( ti_sysbios_interfaces_ITimer_Handle inst, xdc_runtime_Types_Label *lab )
{
    return inst->__fxns->__sysp->__label(inst, lab);
}

 
static inline xdc_runtime_Types_ModuleId ti_sysbios_interfaces_ITimer_Module_id( ti_sysbios_interfaces_ITimer_Module mod )
{
    return mod->__sysp->__mid;
}

 
static inline xdc_UInt ti_sysbios_interfaces_ITimer_getNumTimers( ti_sysbios_interfaces_ITimer_Module __inst )
{
    return __inst->getNumTimers();
}

 
static inline ti_sysbios_interfaces_ITimer_Status ti_sysbios_interfaces_ITimer_getStatus( ti_sysbios_interfaces_ITimer_Module __inst, xdc_UInt id )
{
    return __inst->getStatus(id);
}

 
static inline void ti_sysbios_interfaces_ITimer_startup( ti_sysbios_interfaces_ITimer_Module __inst )
{
    __inst->startup();
}

 
static inline xdc_UInt32 ti_sysbios_interfaces_ITimer_getMaxTicks( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return __inst->__fxns->getMaxTicks((void*)__inst);
}

 
static inline void ti_sysbios_interfaces_ITimer_setNextTick( ti_sysbios_interfaces_ITimer_Handle __inst, xdc_UInt32 ticks )
{
    __inst->__fxns->setNextTick((void*)__inst, ticks);
}

 
static inline void ti_sysbios_interfaces_ITimer_start( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    __inst->__fxns->start((void*)__inst);
}

 
static inline void ti_sysbios_interfaces_ITimer_stop( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    __inst->__fxns->stop((void*)__inst);
}

 
static inline void ti_sysbios_interfaces_ITimer_setPeriod( ti_sysbios_interfaces_ITimer_Handle __inst, xdc_UInt32 period )
{
    __inst->__fxns->setPeriod((void*)__inst, period);
}

 
static inline xdc_Bool ti_sysbios_interfaces_ITimer_setPeriodMicroSecs( ti_sysbios_interfaces_ITimer_Handle __inst, xdc_UInt32 microsecs )
{
    return __inst->__fxns->setPeriodMicroSecs((void*)__inst, microsecs);
}

 
static inline xdc_UInt32 ti_sysbios_interfaces_ITimer_getPeriod( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return __inst->__fxns->getPeriod((void*)__inst);
}

 
static inline xdc_UInt32 ti_sysbios_interfaces_ITimer_getCount( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return __inst->__fxns->getCount((void*)__inst);
}

 
static inline void ti_sysbios_interfaces_ITimer_getFreq( ti_sysbios_interfaces_ITimer_Handle __inst, xdc_runtime_Types_FreqHz *freq )
{
    __inst->__fxns->getFreq((void*)__inst, freq);
}

 
static inline ti_sysbios_interfaces_ITimer_FuncPtr ti_sysbios_interfaces_ITimer_getFunc( ti_sysbios_interfaces_ITimer_Handle __inst, xdc_UArg *arg )
{
    return __inst->__fxns->getFunc((void*)__inst, arg);
}

 
static inline void ti_sysbios_interfaces_ITimer_setFunc( ti_sysbios_interfaces_ITimer_Handle __inst, ti_sysbios_interfaces_ITimer_FuncPtr fxn, xdc_UArg arg )
{
    __inst->__fxns->setFunc((void*)__inst, fxn, arg);
}

 
static inline void ti_sysbios_interfaces_ITimer_trigger( ti_sysbios_interfaces_ITimer_Handle __inst, xdc_UInt32 cycles )
{
    __inst->__fxns->trigger((void*)__inst, cycles);
}

 
static inline xdc_UInt32 ti_sysbios_interfaces_ITimer_getExpiredCounts( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return __inst->__fxns->getExpiredCounts((void*)__inst);
}

 
static inline xdc_UInt32 ti_sysbios_interfaces_ITimer_getExpiredTicks( ti_sysbios_interfaces_ITimer_Handle __inst, xdc_UInt32 tickPeriod )
{
    return __inst->__fxns->getExpiredTicks((void*)__inst, tickPeriod);
}

 
static inline xdc_UInt32 ti_sysbios_interfaces_ITimer_getCurrentTick( ti_sysbios_interfaces_ITimer_Handle __inst, xdc_Bool save )
{
    return __inst->__fxns->getCurrentTick((void*)__inst, save);
}




 






 

 
typedef xdc_UInt (*ti_sysbios_interfaces_ITimer_getNumTimers_FxnT)(void);
static inline ti_sysbios_interfaces_ITimer_getNumTimers_FxnT ti_sysbios_interfaces_ITimer_getNumTimers_fxnP( ti_sysbios_interfaces_ITimer_Module __inst )
{
    return (ti_sysbios_interfaces_ITimer_getNumTimers_FxnT)__inst->getNumTimers;
}

 
typedef ti_sysbios_interfaces_ITimer_Status (*ti_sysbios_interfaces_ITimer_getStatus_FxnT)(xdc_UInt);
static inline ti_sysbios_interfaces_ITimer_getStatus_FxnT ti_sysbios_interfaces_ITimer_getStatus_fxnP( ti_sysbios_interfaces_ITimer_Module __inst )
{
    return (ti_sysbios_interfaces_ITimer_getStatus_FxnT)__inst->getStatus;
}

 
typedef void (*ti_sysbios_interfaces_ITimer_startup_FxnT)(void);
static inline ti_sysbios_interfaces_ITimer_startup_FxnT ti_sysbios_interfaces_ITimer_startup_fxnP( ti_sysbios_interfaces_ITimer_Module __inst )
{
    return (ti_sysbios_interfaces_ITimer_startup_FxnT)__inst->startup;
}

 
typedef xdc_UInt32 (*ti_sysbios_interfaces_ITimer_getMaxTicks_FxnT)(void *);
static inline ti_sysbios_interfaces_ITimer_getMaxTicks_FxnT ti_sysbios_interfaces_ITimer_getMaxTicks_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_getMaxTicks_FxnT)__inst->__fxns->getMaxTicks;
}

 
typedef void (*ti_sysbios_interfaces_ITimer_setNextTick_FxnT)(void *, xdc_UInt32);
static inline ti_sysbios_interfaces_ITimer_setNextTick_FxnT ti_sysbios_interfaces_ITimer_setNextTick_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_setNextTick_FxnT)__inst->__fxns->setNextTick;
}

 
typedef void (*ti_sysbios_interfaces_ITimer_start_FxnT)(void *);
static inline ti_sysbios_interfaces_ITimer_start_FxnT ti_sysbios_interfaces_ITimer_start_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_start_FxnT)__inst->__fxns->start;
}

 
typedef void (*ti_sysbios_interfaces_ITimer_stop_FxnT)(void *);
static inline ti_sysbios_interfaces_ITimer_stop_FxnT ti_sysbios_interfaces_ITimer_stop_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_stop_FxnT)__inst->__fxns->stop;
}

 
typedef void (*ti_sysbios_interfaces_ITimer_setPeriod_FxnT)(void *, xdc_UInt32);
static inline ti_sysbios_interfaces_ITimer_setPeriod_FxnT ti_sysbios_interfaces_ITimer_setPeriod_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_setPeriod_FxnT)__inst->__fxns->setPeriod;
}

 
typedef xdc_Bool (*ti_sysbios_interfaces_ITimer_setPeriodMicroSecs_FxnT)(void *, xdc_UInt32);
static inline ti_sysbios_interfaces_ITimer_setPeriodMicroSecs_FxnT ti_sysbios_interfaces_ITimer_setPeriodMicroSecs_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_setPeriodMicroSecs_FxnT)__inst->__fxns->setPeriodMicroSecs;
}

 
typedef xdc_UInt32 (*ti_sysbios_interfaces_ITimer_getPeriod_FxnT)(void *);
static inline ti_sysbios_interfaces_ITimer_getPeriod_FxnT ti_sysbios_interfaces_ITimer_getPeriod_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_getPeriod_FxnT)__inst->__fxns->getPeriod;
}

 
typedef xdc_UInt32 (*ti_sysbios_interfaces_ITimer_getCount_FxnT)(void *);
static inline ti_sysbios_interfaces_ITimer_getCount_FxnT ti_sysbios_interfaces_ITimer_getCount_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_getCount_FxnT)__inst->__fxns->getCount;
}

 
typedef void (*ti_sysbios_interfaces_ITimer_getFreq_FxnT)(void *, xdc_runtime_Types_FreqHz*);
static inline ti_sysbios_interfaces_ITimer_getFreq_FxnT ti_sysbios_interfaces_ITimer_getFreq_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_getFreq_FxnT)__inst->__fxns->getFreq;
}

 
typedef ti_sysbios_interfaces_ITimer_FuncPtr (*ti_sysbios_interfaces_ITimer_getFunc_FxnT)(void *, xdc_UArg*);
static inline ti_sysbios_interfaces_ITimer_getFunc_FxnT ti_sysbios_interfaces_ITimer_getFunc_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_getFunc_FxnT)__inst->__fxns->getFunc;
}

 
typedef void (*ti_sysbios_interfaces_ITimer_setFunc_FxnT)(void *, ti_sysbios_interfaces_ITimer_FuncPtr, xdc_UArg);
static inline ti_sysbios_interfaces_ITimer_setFunc_FxnT ti_sysbios_interfaces_ITimer_setFunc_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_setFunc_FxnT)__inst->__fxns->setFunc;
}

 
typedef void (*ti_sysbios_interfaces_ITimer_trigger_FxnT)(void *, xdc_UInt32);
static inline ti_sysbios_interfaces_ITimer_trigger_FxnT ti_sysbios_interfaces_ITimer_trigger_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_trigger_FxnT)__inst->__fxns->trigger;
}

 
typedef xdc_UInt32 (*ti_sysbios_interfaces_ITimer_getExpiredCounts_FxnT)(void *);
static inline ti_sysbios_interfaces_ITimer_getExpiredCounts_FxnT ti_sysbios_interfaces_ITimer_getExpiredCounts_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_getExpiredCounts_FxnT)__inst->__fxns->getExpiredCounts;
}

 
typedef xdc_UInt32 (*ti_sysbios_interfaces_ITimer_getExpiredTicks_FxnT)(void *, xdc_UInt32);
static inline ti_sysbios_interfaces_ITimer_getExpiredTicks_FxnT ti_sysbios_interfaces_ITimer_getExpiredTicks_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_getExpiredTicks_FxnT)__inst->__fxns->getExpiredTicks;
}

 
typedef xdc_UInt32 (*ti_sysbios_interfaces_ITimer_getCurrentTick_FxnT)(void *, xdc_Bool);
static inline ti_sysbios_interfaces_ITimer_getCurrentTick_FxnT ti_sysbios_interfaces_ITimer_getCurrentTick_fxnP( ti_sysbios_interfaces_ITimer_Handle __inst )
{
    return (ti_sysbios_interfaces_ITimer_getCurrentTick_FxnT)__inst->__fxns->getCurrentTick;
}




 






 






 
















 




 





 





 






 

















 




 








 







 















 




 





 




 



 






 














 




 





 



 







 















 




 





 





 



 






 















 




 





 




 



 






 















 




 





 




 



 






 















 




 





 




 



 






 
















 




 





 





 






 













 




 





 



 





 

 
typedef void (*ti_sysbios_knl_Swi_FuncPtr)(xdc_UArg, xdc_UArg);

 
struct ti_sysbios_knl_Swi_HookSet {
    void (*registerFxn)(xdc_Int);
    void (*createFxn)(ti_sysbios_knl_Swi_Handle, xdc_runtime_Error_Block*);
    void (*readyFxn)(ti_sysbios_knl_Swi_Handle);
    void (*beginFxn)(ti_sysbios_knl_Swi_Handle);
    void (*endFxn)(ti_sysbios_knl_Swi_Handle);
    void (*deleteFxn)(ti_sysbios_knl_Swi_Handle);
};




 

 
typedef struct ti_sysbios_knl_Swi_Args__create {
    ti_sysbios_knl_Swi_FuncPtr swiFxn;
} ti_sysbios_knl_Swi_Args__create;




 

 
typedef xdc_Ptr __T1_ti_sysbios_knl_Swi_Instance_State__hookEnv;
typedef xdc_Ptr *__ARRAY1_ti_sysbios_knl_Swi_Instance_State__hookEnv;
typedef __ARRAY1_ti_sysbios_knl_Swi_Instance_State__hookEnv __TA_ti_sysbios_knl_Swi_Instance_State__hookEnv;

 
typedef ti_sysbios_knl_Queue_Instance_State __T1_ti_sysbios_knl_Swi_Module_State__readyQ;
typedef ti_sysbios_knl_Queue_Instance_State *__ARRAY1_ti_sysbios_knl_Swi_Module_State__readyQ;
typedef __ARRAY1_ti_sysbios_knl_Swi_Module_State__readyQ __TA_ti_sysbios_knl_Swi_Module_State__readyQ;
typedef ti_sysbios_knl_Swi_Handle __T1_ti_sysbios_knl_Swi_Module_State__constructedSwis;
typedef ti_sysbios_knl_Swi_Handle *__ARRAY1_ti_sysbios_knl_Swi_Module_State__constructedSwis;
typedef __ARRAY1_ti_sysbios_knl_Swi_Module_State__constructedSwis __TA_ti_sysbios_knl_Swi_Module_State__constructedSwis;




 

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Swi_Module__diagsEnabled;
extern  const CT__ti_sysbios_knl_Swi_Module__diagsEnabled ti_sysbios_knl_Swi_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Swi_Module__diagsIncluded;
extern  const CT__ti_sysbios_knl_Swi_Module__diagsIncluded ti_sysbios_knl_Swi_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__ti_sysbios_knl_Swi_Module__diagsMask;
extern  const CT__ti_sysbios_knl_Swi_Module__diagsMask ti_sysbios_knl_Swi_Module__diagsMask__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Swi_Module__gateObj;
extern  const CT__ti_sysbios_knl_Swi_Module__gateObj ti_sysbios_knl_Swi_Module__gateObj__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Swi_Module__gatePrms;
extern  const CT__ti_sysbios_knl_Swi_Module__gatePrms ti_sysbios_knl_Swi_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__ti_sysbios_knl_Swi_Module__id;
extern  const CT__ti_sysbios_knl_Swi_Module__id ti_sysbios_knl_Swi_Module__id__C;

 
typedef xdc_Bool CT__ti_sysbios_knl_Swi_Module__loggerDefined;
extern  const CT__ti_sysbios_knl_Swi_Module__loggerDefined ti_sysbios_knl_Swi_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Swi_Module__loggerObj;
extern  const CT__ti_sysbios_knl_Swi_Module__loggerObj ti_sysbios_knl_Swi_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__ti_sysbios_knl_Swi_Module__loggerFxn0;
extern  const CT__ti_sysbios_knl_Swi_Module__loggerFxn0 ti_sysbios_knl_Swi_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__ti_sysbios_knl_Swi_Module__loggerFxn1;
extern  const CT__ti_sysbios_knl_Swi_Module__loggerFxn1 ti_sysbios_knl_Swi_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__ti_sysbios_knl_Swi_Module__loggerFxn2;
extern  const CT__ti_sysbios_knl_Swi_Module__loggerFxn2 ti_sysbios_knl_Swi_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__ti_sysbios_knl_Swi_Module__loggerFxn4;
extern  const CT__ti_sysbios_knl_Swi_Module__loggerFxn4 ti_sysbios_knl_Swi_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__ti_sysbios_knl_Swi_Module__loggerFxn8;
extern  const CT__ti_sysbios_knl_Swi_Module__loggerFxn8 ti_sysbios_knl_Swi_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__ti_sysbios_knl_Swi_Module__startupDoneFxn)(void);
extern  const CT__ti_sysbios_knl_Swi_Module__startupDoneFxn ti_sysbios_knl_Swi_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__ti_sysbios_knl_Swi_Object__count;
extern  const CT__ti_sysbios_knl_Swi_Object__count ti_sysbios_knl_Swi_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__ti_sysbios_knl_Swi_Object__heap;
extern  const CT__ti_sysbios_knl_Swi_Object__heap ti_sysbios_knl_Swi_Object__heap__C;

 
typedef xdc_SizeT CT__ti_sysbios_knl_Swi_Object__sizeof;
extern  const CT__ti_sysbios_knl_Swi_Object__sizeof ti_sysbios_knl_Swi_Object__sizeof__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Swi_Object__table;
extern  const CT__ti_sysbios_knl_Swi_Object__table ti_sysbios_knl_Swi_Object__table__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Swi_LM_begin;
extern  const CT__ti_sysbios_knl_Swi_LM_begin ti_sysbios_knl_Swi_LM_begin__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Swi_LD_end;
extern  const CT__ti_sysbios_knl_Swi_LD_end ti_sysbios_knl_Swi_LD_end__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Swi_LM_post;
extern  const CT__ti_sysbios_knl_Swi_LM_post ti_sysbios_knl_Swi_LM_post__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Swi_A_swiDisabled;
extern  const CT__ti_sysbios_knl_Swi_A_swiDisabled ti_sysbios_knl_Swi_A_swiDisabled__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Swi_A_badPriority;
extern  const CT__ti_sysbios_knl_Swi_A_badPriority ti_sysbios_knl_Swi_A_badPriority__C;

 
typedef xdc_UInt CT__ti_sysbios_knl_Swi_numPriorities;
extern  const CT__ti_sysbios_knl_Swi_numPriorities ti_sysbios_knl_Swi_numPriorities__C;

 
typedef ti_sysbios_knl_Swi_HookSet __T1_ti_sysbios_knl_Swi_hooks;
typedef struct { int length; ti_sysbios_knl_Swi_HookSet *elem; } __ARRAY1_ti_sysbios_knl_Swi_hooks;
typedef __ARRAY1_ti_sysbios_knl_Swi_hooks __TA_ti_sysbios_knl_Swi_hooks;
typedef __TA_ti_sysbios_knl_Swi_hooks CT__ti_sysbios_knl_Swi_hooks;
extern  const CT__ti_sysbios_knl_Swi_hooks ti_sysbios_knl_Swi_hooks__C;

 
typedef xdc_UInt (*CT__ti_sysbios_knl_Swi_taskDisable)(void);
extern  const CT__ti_sysbios_knl_Swi_taskDisable ti_sysbios_knl_Swi_taskDisable__C;

 
typedef void (*CT__ti_sysbios_knl_Swi_taskRestore)(xdc_UInt);
extern  const CT__ti_sysbios_knl_Swi_taskRestore ti_sysbios_knl_Swi_taskRestore__C;

 
typedef xdc_UInt CT__ti_sysbios_knl_Swi_numConstructedSwis;
extern  const CT__ti_sysbios_knl_Swi_numConstructedSwis ti_sysbios_knl_Swi_numConstructedSwis__C;




 

 
struct ti_sysbios_knl_Swi_Params {
    size_t __size;
    const void *__self;
    void *__fxns;
    xdc_runtime_IInstance_Params *instance;
    xdc_UArg arg0;
    xdc_UArg arg1;
    xdc_UInt priority;
    xdc_UInt trigger;
    xdc_runtime_IInstance_Params __iprms;
};

 
struct ti_sysbios_knl_Swi_Struct {
    ti_sysbios_knl_Queue_Elem __f0;
    ti_sysbios_knl_Swi_FuncPtr __f1;
    xdc_UArg __f2;
    xdc_UArg __f3;
    xdc_UInt __f4;
    xdc_UInt __f5;
    xdc_Bool __f6;
    xdc_UInt __f7;
    xdc_UInt __f8;
    ti_sysbios_knl_Queue_Handle __f9;
    __TA_ti_sysbios_knl_Swi_Instance_State__hookEnv __f10;
    xdc_runtime_Types_CordAddr __name;
};




 

 

extern xdc_Int ti_sysbios_knl_Swi_Module_startup__E( xdc_Int state );

extern xdc_Int ti_sysbios_knl_Swi_Module_startup__F( xdc_Int state );

 

extern xdc_Int ti_sysbios_knl_Swi_Instance_init__E(ti_sysbios_knl_Swi_Object *, ti_sysbios_knl_Swi_FuncPtr swiFxn, const ti_sysbios_knl_Swi_Params *, xdc_runtime_Error_Block *);

 

extern void ti_sysbios_knl_Swi_Instance_finalize__E( ti_sysbios_knl_Swi_Object* , int );

 

extern xdc_runtime_Types_Label *ti_sysbios_knl_Swi_Handle__label__S( xdc_Ptr obj, xdc_runtime_Types_Label *lab );

 

extern xdc_Bool ti_sysbios_knl_Swi_Module__startupDone__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Swi_Object__create__S( xdc_Ptr __oa, xdc_SizeT __osz, xdc_Ptr __aa, const xdc_UChar *__pa, xdc_SizeT __psz, xdc_runtime_Error_Block *__eb );

 

extern ti_sysbios_knl_Swi_Handle ti_sysbios_knl_Swi_create( ti_sysbios_knl_Swi_FuncPtr swiFxn, const ti_sysbios_knl_Swi_Params *__prms, xdc_runtime_Error_Block *__eb );

 

extern void ti_sysbios_knl_Swi_construct( ti_sysbios_knl_Swi_Struct *__obj, ti_sysbios_knl_Swi_FuncPtr swiFxn, const ti_sysbios_knl_Swi_Params *__prms, xdc_runtime_Error_Block *__eb );

 

extern void ti_sysbios_knl_Swi_Object__delete__S( xdc_Ptr instp );

 

extern void ti_sysbios_knl_Swi_delete(ti_sysbios_knl_Swi_Handle *instp);

 

extern void ti_sysbios_knl_Swi_Object__destruct__S( xdc_Ptr objp );

 

extern void ti_sysbios_knl_Swi_destruct(ti_sysbios_knl_Swi_Struct *obj);

 

extern xdc_Ptr ti_sysbios_knl_Swi_Object__get__S( xdc_Ptr oarr, xdc_Int i );

 

extern xdc_Ptr ti_sysbios_knl_Swi_Object__first__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Swi_Object__next__S( xdc_Ptr obj );

 

extern void ti_sysbios_knl_Swi_Params__init__S( xdc_Ptr dst, const void *src, xdc_SizeT psz, xdc_SizeT isz );

 

extern void ti_sysbios_knl_Swi_startup__E( void );

 

extern xdc_Bool ti_sysbios_knl_Swi_enabled__E( void );

 

extern void ti_sysbios_knl_Swi_unlockSched__E( void );

 

extern xdc_UInt ti_sysbios_knl_Swi_disable__E( void );

 

extern void ti_sysbios_knl_Swi_enable__E( void );

 

extern void ti_sysbios_knl_Swi_restore__E( xdc_UInt key );

 

extern void ti_sysbios_knl_Swi_restoreHwi__E( xdc_UInt key );

 

extern ti_sysbios_knl_Swi_Handle ti_sysbios_knl_Swi_self__E( void );

 

extern xdc_UInt ti_sysbios_knl_Swi_getTrigger__E( void );

 

extern xdc_UInt ti_sysbios_knl_Swi_raisePri__E( xdc_UInt priority );

 

extern void ti_sysbios_knl_Swi_restorePri__E( xdc_UInt key );

 

extern void ti_sysbios_knl_Swi_andn__E( ti_sysbios_knl_Swi_Handle __inst, xdc_UInt mask );

 

extern void ti_sysbios_knl_Swi_dec__E( ti_sysbios_knl_Swi_Handle __inst );

 

extern xdc_Ptr ti_sysbios_knl_Swi_getHookContext__E( ti_sysbios_knl_Swi_Handle __inst, xdc_Int id );

 

extern void ti_sysbios_knl_Swi_setHookContext__E( ti_sysbios_knl_Swi_Handle __inst, xdc_Int id, xdc_Ptr hookContext );

 

extern xdc_UInt ti_sysbios_knl_Swi_getPri__E( ti_sysbios_knl_Swi_Handle __inst );

 

extern ti_sysbios_knl_Swi_FuncPtr ti_sysbios_knl_Swi_getFunc__E( ti_sysbios_knl_Swi_Handle __inst, xdc_UArg *arg0, xdc_UArg *arg1 );

 

extern void ti_sysbios_knl_Swi_getAttrs__E( ti_sysbios_knl_Swi_Handle __inst, ti_sysbios_knl_Swi_FuncPtr *swiFxn, ti_sysbios_knl_Swi_Params *params );

 

extern void ti_sysbios_knl_Swi_setAttrs__E( ti_sysbios_knl_Swi_Handle __inst, ti_sysbios_knl_Swi_FuncPtr swiFxn, ti_sysbios_knl_Swi_Params *params );

 

extern void ti_sysbios_knl_Swi_inc__E( ti_sysbios_knl_Swi_Handle __inst );

 

extern void ti_sysbios_knl_Swi_or__E( ti_sysbios_knl_Swi_Handle __inst, xdc_UInt mask );

 

extern void ti_sysbios_knl_Swi_post__E( ti_sysbios_knl_Swi_Handle __inst );

 

extern void ti_sysbios_knl_Swi_schedule__I( void );

 

extern void ti_sysbios_knl_Swi_runLoop__I( void );

 

extern void ti_sysbios_knl_Swi_run__I( ti_sysbios_knl_Swi_Object *swi );

 

extern xdc_Int ti_sysbios_knl_Swi_postInit__I( ti_sysbios_knl_Swi_Object *swi, xdc_runtime_Error_Block *eb );

 

extern void ti_sysbios_knl_Swi_restoreSMP__I( void );




 

 

 

 

 
static inline CT__ti_sysbios_knl_Swi_Module__id ti_sysbios_knl_Swi_Module_id( void ) 
{
    return ti_sysbios_knl_Swi_Module__id__C;
}

 
static inline xdc_Bool ti_sysbios_knl_Swi_Module_hasMask( void ) 
{
    return ti_sysbios_knl_Swi_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 ti_sysbios_knl_Swi_Module_getMask( void ) 
{
    return ti_sysbios_knl_Swi_Module__diagsMask__C != 0 ? *ti_sysbios_knl_Swi_Module__diagsMask__C : 0;
}

 
static inline void ti_sysbios_knl_Swi_Module_setMask( xdc_Bits16 mask ) 
{
    if (ti_sysbios_knl_Swi_Module__diagsMask__C != 0) *ti_sysbios_knl_Swi_Module__diagsMask__C = mask;
}

 
static inline void ti_sysbios_knl_Swi_Params_init( ti_sysbios_knl_Swi_Params *prms ) 
{
    if (prms) {
        ti_sysbios_knl_Swi_Params__init__S(prms, 0, sizeof(ti_sysbios_knl_Swi_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 
static inline void ti_sysbios_knl_Swi_Params_copy(ti_sysbios_knl_Swi_Params *dst, const ti_sysbios_knl_Swi_Params *src) 
{
    if (dst) {
        ti_sysbios_knl_Swi_Params__init__S(dst, (const void *)src, sizeof(ti_sysbios_knl_Swi_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 

 

 
static inline ti_sysbios_knl_Swi_Handle ti_sysbios_knl_Swi_Object_get(ti_sysbios_knl_Swi_Instance_State *oarr, int i) 
{
    return (ti_sysbios_knl_Swi_Handle)ti_sysbios_knl_Swi_Object__get__S(oarr, i);
}

 
static inline ti_sysbios_knl_Swi_Handle ti_sysbios_knl_Swi_Object_first( void )
{
    return (ti_sysbios_knl_Swi_Handle)ti_sysbios_knl_Swi_Object__first__S();
}

 
static inline ti_sysbios_knl_Swi_Handle ti_sysbios_knl_Swi_Object_next( ti_sysbios_knl_Swi_Object *obj )
{
    return (ti_sysbios_knl_Swi_Handle)ti_sysbios_knl_Swi_Object__next__S(obj);
}

 
static inline xdc_runtime_Types_Label *ti_sysbios_knl_Swi_Handle_label( ti_sysbios_knl_Swi_Handle inst, xdc_runtime_Types_Label *lab )
{
    return ti_sysbios_knl_Swi_Handle__label__S(inst, lab);
}

 
static inline xdc_String ti_sysbios_knl_Swi_Handle_name( ti_sysbios_knl_Swi_Handle inst )
{
    xdc_runtime_Types_Label lab;
    return ti_sysbios_knl_Swi_Handle__label__S(inst, &lab)->iname;
}

 
static inline ti_sysbios_knl_Swi_Handle ti_sysbios_knl_Swi_handle( ti_sysbios_knl_Swi_Struct *str )
{
    return (ti_sysbios_knl_Swi_Handle)str;
}

 
static inline ti_sysbios_knl_Swi_Struct *ti_sysbios_knl_Swi_struct( ti_sysbios_knl_Swi_Handle inst )
{
    return (ti_sysbios_knl_Swi_Struct*)inst;
}




 






 





 






 

















 




 








 







 















 




 





 




 



 






 














 




 





 



 







 















 




 





 





 

 
typedef ti_sysbios_interfaces_ITimer_FuncPtr ti_sysbios_knl_Clock_TimerProxy_FuncPtr;

 

 
typedef ti_sysbios_interfaces_ITimer_StartMode ti_sysbios_knl_Clock_TimerProxy_StartMode;

 
typedef ti_sysbios_interfaces_ITimer_RunMode ti_sysbios_knl_Clock_TimerProxy_RunMode;

 
typedef ti_sysbios_interfaces_ITimer_Status ti_sysbios_knl_Clock_TimerProxy_Status;

 
typedef ti_sysbios_interfaces_ITimer_PeriodType ti_sysbios_knl_Clock_TimerProxy_PeriodType;




 

 
typedef struct ti_sysbios_knl_Clock_TimerProxy_Args__create {
    xdc_Int id;
    ti_sysbios_interfaces_ITimer_FuncPtr tickFxn;
} ti_sysbios_knl_Clock_TimerProxy_Args__create;




 

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Clock_TimerProxy_Module__diagsEnabled;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Module__diagsEnabled ti_sysbios_knl_Clock_TimerProxy_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Clock_TimerProxy_Module__diagsIncluded;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Module__diagsIncluded ti_sysbios_knl_Clock_TimerProxy_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__ti_sysbios_knl_Clock_TimerProxy_Module__diagsMask;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Module__diagsMask ti_sysbios_knl_Clock_TimerProxy_Module__diagsMask__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Clock_TimerProxy_Module__gateObj;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Module__gateObj ti_sysbios_knl_Clock_TimerProxy_Module__gateObj__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Clock_TimerProxy_Module__gatePrms;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Module__gatePrms ti_sysbios_knl_Clock_TimerProxy_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__ti_sysbios_knl_Clock_TimerProxy_Module__id;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Module__id ti_sysbios_knl_Clock_TimerProxy_Module__id__C;

 
typedef xdc_Bool CT__ti_sysbios_knl_Clock_TimerProxy_Module__loggerDefined;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Module__loggerDefined ti_sysbios_knl_Clock_TimerProxy_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Clock_TimerProxy_Module__loggerObj;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Module__loggerObj ti_sysbios_knl_Clock_TimerProxy_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn0;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn0 ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn1;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn1 ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn2;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn2 ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn4;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn4 ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn8;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn8 ti_sysbios_knl_Clock_TimerProxy_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__ti_sysbios_knl_Clock_TimerProxy_Module__startupDoneFxn)(void);
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Module__startupDoneFxn ti_sysbios_knl_Clock_TimerProxy_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__ti_sysbios_knl_Clock_TimerProxy_Object__count;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Object__count ti_sysbios_knl_Clock_TimerProxy_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__ti_sysbios_knl_Clock_TimerProxy_Object__heap;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Object__heap ti_sysbios_knl_Clock_TimerProxy_Object__heap__C;

 
typedef xdc_SizeT CT__ti_sysbios_knl_Clock_TimerProxy_Object__sizeof;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Object__sizeof ti_sysbios_knl_Clock_TimerProxy_Object__sizeof__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Clock_TimerProxy_Object__table;
extern  const CT__ti_sysbios_knl_Clock_TimerProxy_Object__table ti_sysbios_knl_Clock_TimerProxy_Object__table__C;




 

 
struct ti_sysbios_knl_Clock_TimerProxy_Params {
    size_t __size;
    const void *__self;
    void *__fxns;
    xdc_runtime_IInstance_Params *instance;
    ti_sysbios_interfaces_ITimer_RunMode runMode;
    ti_sysbios_interfaces_ITimer_StartMode startMode;
    xdc_UArg arg;
    xdc_UInt32 period;
    ti_sysbios_interfaces_ITimer_PeriodType periodType;
    xdc_runtime_Types_FreqHz extFreq;
    xdc_runtime_IInstance_Params __iprms;
};

 
struct ti_sysbios_knl_Clock_TimerProxy_Struct {
    const ti_sysbios_knl_Clock_TimerProxy_Fxns__ *__fxns;
    xdc_runtime_Types_CordAddr __name;
};




 

 
struct ti_sysbios_knl_Clock_TimerProxy_Fxns__ {
    xdc_runtime_Types_Base* __base;
    const xdc_runtime_Types_SysFxns2 *__sysp;
    xdc_UInt (*getNumTimers)(void);
    ti_sysbios_interfaces_ITimer_Status (*getStatus)(xdc_UInt);
    void (*startup)(void);
    xdc_UInt32 (*getMaxTicks)(ti_sysbios_knl_Clock_TimerProxy_Handle);
    void (*setNextTick)(ti_sysbios_knl_Clock_TimerProxy_Handle, xdc_UInt32);
    void (*start)(ti_sysbios_knl_Clock_TimerProxy_Handle);
    void (*stop)(ti_sysbios_knl_Clock_TimerProxy_Handle);
    void (*setPeriod)(ti_sysbios_knl_Clock_TimerProxy_Handle, xdc_UInt32);
    xdc_Bool (*setPeriodMicroSecs)(ti_sysbios_knl_Clock_TimerProxy_Handle, xdc_UInt32);
    xdc_UInt32 (*getPeriod)(ti_sysbios_knl_Clock_TimerProxy_Handle);
    xdc_UInt32 (*getCount)(ti_sysbios_knl_Clock_TimerProxy_Handle);
    void (*getFreq)(ti_sysbios_knl_Clock_TimerProxy_Handle, xdc_runtime_Types_FreqHz*);
    ti_sysbios_interfaces_ITimer_FuncPtr (*getFunc)(ti_sysbios_knl_Clock_TimerProxy_Handle, xdc_UArg*);
    void (*setFunc)(ti_sysbios_knl_Clock_TimerProxy_Handle, ti_sysbios_interfaces_ITimer_FuncPtr, xdc_UArg);
    void (*trigger)(ti_sysbios_knl_Clock_TimerProxy_Handle, xdc_UInt32);
    xdc_UInt32 (*getExpiredCounts)(ti_sysbios_knl_Clock_TimerProxy_Handle);
    xdc_UInt32 (*getExpiredTicks)(ti_sysbios_knl_Clock_TimerProxy_Handle, xdc_UInt32);
    xdc_UInt32 (*getCurrentTick)(ti_sysbios_knl_Clock_TimerProxy_Handle, xdc_Bool);
    xdc_runtime_Types_SysFxns2 __sfxns;
};

 
extern const ti_sysbios_knl_Clock_TimerProxy_Fxns__ ti_sysbios_knl_Clock_TimerProxy_Module__FXNS__C;




 

 

 

extern xdc_runtime_Types_Label *ti_sysbios_knl_Clock_TimerProxy_Handle__label__S( xdc_Ptr obj, xdc_runtime_Types_Label *lab );

 

extern xdc_Bool ti_sysbios_knl_Clock_TimerProxy_Module__startupDone__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Clock_TimerProxy_Object__create__S( xdc_Ptr __oa, xdc_SizeT __osz, xdc_Ptr __aa, const xdc_UChar *__pa, xdc_SizeT __psz, xdc_runtime_Error_Block *__eb );

 

extern ti_sysbios_knl_Clock_TimerProxy_Handle ti_sysbios_knl_Clock_TimerProxy_create( xdc_Int id, ti_sysbios_interfaces_ITimer_FuncPtr tickFxn, const ti_sysbios_knl_Clock_TimerProxy_Params *__prms, xdc_runtime_Error_Block *__eb );

 

extern void ti_sysbios_knl_Clock_TimerProxy_Object__delete__S( xdc_Ptr instp );

 

extern void ti_sysbios_knl_Clock_TimerProxy_delete(ti_sysbios_knl_Clock_TimerProxy_Handle *instp);

 

extern void ti_sysbios_knl_Clock_TimerProxy_Object__destruct__S( xdc_Ptr objp );

 

extern xdc_Ptr ti_sysbios_knl_Clock_TimerProxy_Object__get__S( xdc_Ptr oarr, xdc_Int i );

 

extern xdc_Ptr ti_sysbios_knl_Clock_TimerProxy_Object__first__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Clock_TimerProxy_Object__next__S( xdc_Ptr obj );

 

extern void ti_sysbios_knl_Clock_TimerProxy_Params__init__S( xdc_Ptr dst, const void *src, xdc_SizeT psz, xdc_SizeT isz );

 

extern xdc_Bool ti_sysbios_knl_Clock_TimerProxy_Proxy__abstract__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Clock_TimerProxy_Proxy__delegate__S( void );

 

extern xdc_UInt ti_sysbios_knl_Clock_TimerProxy_getNumTimers__E( void );

 

extern ti_sysbios_interfaces_ITimer_Status ti_sysbios_knl_Clock_TimerProxy_getStatus__E( xdc_UInt id );

 

extern void ti_sysbios_knl_Clock_TimerProxy_startup__E( void );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_TimerProxy_getMaxTicks__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst );

 

extern void ti_sysbios_knl_Clock_TimerProxy_setNextTick__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst, xdc_UInt32 ticks );

 

extern void ti_sysbios_knl_Clock_TimerProxy_start__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst );

 

extern void ti_sysbios_knl_Clock_TimerProxy_stop__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst );

 

extern void ti_sysbios_knl_Clock_TimerProxy_setPeriod__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst, xdc_UInt32 period );

 

extern xdc_Bool ti_sysbios_knl_Clock_TimerProxy_setPeriodMicroSecs__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst, xdc_UInt32 microsecs );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_TimerProxy_getPeriod__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_TimerProxy_getCount__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst );

 

extern void ti_sysbios_knl_Clock_TimerProxy_getFreq__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst, xdc_runtime_Types_FreqHz *freq );

 

extern ti_sysbios_interfaces_ITimer_FuncPtr ti_sysbios_knl_Clock_TimerProxy_getFunc__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst, xdc_UArg *arg );

 

extern void ti_sysbios_knl_Clock_TimerProxy_setFunc__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst, ti_sysbios_interfaces_ITimer_FuncPtr fxn, xdc_UArg arg );

 

extern void ti_sysbios_knl_Clock_TimerProxy_trigger__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst, xdc_UInt32 cycles );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_TimerProxy_getExpiredCounts__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_TimerProxy_getExpiredTicks__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst, xdc_UInt32 tickPeriod );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_TimerProxy_getCurrentTick__E( ti_sysbios_knl_Clock_TimerProxy_Handle __inst, xdc_Bool save );




 

 
static inline ti_sysbios_interfaces_ITimer_Module ti_sysbios_knl_Clock_TimerProxy_Module_upCast( void )
{
    return (ti_sysbios_interfaces_ITimer_Module)ti_sysbios_knl_Clock_TimerProxy_Proxy__delegate__S();
}

 

 
static inline ti_sysbios_interfaces_ITimer_Handle ti_sysbios_knl_Clock_TimerProxy_Handle_upCast( ti_sysbios_knl_Clock_TimerProxy_Handle i )
{
    return (ti_sysbios_interfaces_ITimer_Handle)i;
}

 

 
static inline ti_sysbios_knl_Clock_TimerProxy_Handle ti_sysbios_knl_Clock_TimerProxy_Handle_downCast( ti_sysbios_interfaces_ITimer_Handle i )
{
    ti_sysbios_interfaces_ITimer_Handle i2 = (ti_sysbios_interfaces_ITimer_Handle)i;
if (ti_sysbios_knl_Clock_TimerProxy_Proxy__abstract__S()) return (ti_sysbios_knl_Clock_TimerProxy_Handle)i;
    return (void*)i2->__fxns == (void*)ti_sysbios_knl_Clock_TimerProxy_Proxy__delegate__S() ? (ti_sysbios_knl_Clock_TimerProxy_Handle)i : 0;
}

 




 

 

 

 

 
static inline CT__ti_sysbios_knl_Clock_TimerProxy_Module__id ti_sysbios_knl_Clock_TimerProxy_Module_id( void ) 
{
    return ti_sysbios_knl_Clock_TimerProxy_Module__id__C;
}

 

 

 
static inline void ti_sysbios_knl_Clock_TimerProxy_Params_init( ti_sysbios_knl_Clock_TimerProxy_Params *prms ) 
{
    if (prms) {
        ti_sysbios_knl_Clock_TimerProxy_Params__init__S(prms, 0, sizeof(ti_sysbios_knl_Clock_TimerProxy_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 
static inline void ti_sysbios_knl_Clock_TimerProxy_Params_copy(ti_sysbios_knl_Clock_TimerProxy_Params *dst, const ti_sysbios_knl_Clock_TimerProxy_Params *src) 
{
    if (dst) {
        ti_sysbios_knl_Clock_TimerProxy_Params__init__S(dst, (const void *)src, sizeof(ti_sysbios_knl_Clock_TimerProxy_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}




 






 





 

 
enum ti_sysbios_knl_Clock_TickSource {
    ti_sysbios_knl_Clock_TickSource_TIMER,
    ti_sysbios_knl_Clock_TickSource_USER,
    ti_sysbios_knl_Clock_TickSource_NULL
};
typedef enum ti_sysbios_knl_Clock_TickSource ti_sysbios_knl_Clock_TickSource;

 
enum ti_sysbios_knl_Clock_TickMode {
    ti_sysbios_knl_Clock_TickMode_PERIODIC,
    ti_sysbios_knl_Clock_TickMode_DYNAMIC
};
typedef enum ti_sysbios_knl_Clock_TickMode ti_sysbios_knl_Clock_TickMode;

 
typedef void (*ti_sysbios_knl_Clock_FuncPtr)(xdc_UArg);




 

 
typedef struct ti_sysbios_knl_Clock_Args__create {
    ti_sysbios_knl_Clock_FuncPtr clockFxn;
    xdc_UInt timeout;
} ti_sysbios_knl_Clock_Args__create;




 




 

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Clock_Module__diagsEnabled;
extern  const CT__ti_sysbios_knl_Clock_Module__diagsEnabled ti_sysbios_knl_Clock_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Clock_Module__diagsIncluded;
extern  const CT__ti_sysbios_knl_Clock_Module__diagsIncluded ti_sysbios_knl_Clock_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__ti_sysbios_knl_Clock_Module__diagsMask;
extern  const CT__ti_sysbios_knl_Clock_Module__diagsMask ti_sysbios_knl_Clock_Module__diagsMask__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Clock_Module__gateObj;
extern  const CT__ti_sysbios_knl_Clock_Module__gateObj ti_sysbios_knl_Clock_Module__gateObj__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Clock_Module__gatePrms;
extern  const CT__ti_sysbios_knl_Clock_Module__gatePrms ti_sysbios_knl_Clock_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__ti_sysbios_knl_Clock_Module__id;
extern  const CT__ti_sysbios_knl_Clock_Module__id ti_sysbios_knl_Clock_Module__id__C;

 
typedef xdc_Bool CT__ti_sysbios_knl_Clock_Module__loggerDefined;
extern  const CT__ti_sysbios_knl_Clock_Module__loggerDefined ti_sysbios_knl_Clock_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Clock_Module__loggerObj;
extern  const CT__ti_sysbios_knl_Clock_Module__loggerObj ti_sysbios_knl_Clock_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__ti_sysbios_knl_Clock_Module__loggerFxn0;
extern  const CT__ti_sysbios_knl_Clock_Module__loggerFxn0 ti_sysbios_knl_Clock_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__ti_sysbios_knl_Clock_Module__loggerFxn1;
extern  const CT__ti_sysbios_knl_Clock_Module__loggerFxn1 ti_sysbios_knl_Clock_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__ti_sysbios_knl_Clock_Module__loggerFxn2;
extern  const CT__ti_sysbios_knl_Clock_Module__loggerFxn2 ti_sysbios_knl_Clock_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__ti_sysbios_knl_Clock_Module__loggerFxn4;
extern  const CT__ti_sysbios_knl_Clock_Module__loggerFxn4 ti_sysbios_knl_Clock_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__ti_sysbios_knl_Clock_Module__loggerFxn8;
extern  const CT__ti_sysbios_knl_Clock_Module__loggerFxn8 ti_sysbios_knl_Clock_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__ti_sysbios_knl_Clock_Module__startupDoneFxn)(void);
extern  const CT__ti_sysbios_knl_Clock_Module__startupDoneFxn ti_sysbios_knl_Clock_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__ti_sysbios_knl_Clock_Object__count;
extern  const CT__ti_sysbios_knl_Clock_Object__count ti_sysbios_knl_Clock_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__ti_sysbios_knl_Clock_Object__heap;
extern  const CT__ti_sysbios_knl_Clock_Object__heap ti_sysbios_knl_Clock_Object__heap__C;

 
typedef xdc_SizeT CT__ti_sysbios_knl_Clock_Object__sizeof;
extern  const CT__ti_sysbios_knl_Clock_Object__sizeof ti_sysbios_knl_Clock_Object__sizeof__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Clock_Object__table;
extern  const CT__ti_sysbios_knl_Clock_Object__table ti_sysbios_knl_Clock_Object__table__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Clock_LW_delayed;
extern  const CT__ti_sysbios_knl_Clock_LW_delayed ti_sysbios_knl_Clock_LW_delayed__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Clock_LM_tick;
extern  const CT__ti_sysbios_knl_Clock_LM_tick ti_sysbios_knl_Clock_LM_tick__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Clock_LM_begin;
extern  const CT__ti_sysbios_knl_Clock_LM_begin ti_sysbios_knl_Clock_LM_begin__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Clock_A_clockDisabled;
extern  const CT__ti_sysbios_knl_Clock_A_clockDisabled ti_sysbios_knl_Clock_A_clockDisabled__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Clock_A_badThreadType;
extern  const CT__ti_sysbios_knl_Clock_A_badThreadType ti_sysbios_knl_Clock_A_badThreadType__C;

 
typedef xdc_UInt32 CT__ti_sysbios_knl_Clock_serviceMargin;
extern  const CT__ti_sysbios_knl_Clock_serviceMargin ti_sysbios_knl_Clock_serviceMargin__C;

 
typedef ti_sysbios_knl_Clock_TickSource CT__ti_sysbios_knl_Clock_tickSource;
extern  const CT__ti_sysbios_knl_Clock_tickSource ti_sysbios_knl_Clock_tickSource__C;

 
typedef ti_sysbios_knl_Clock_TickMode CT__ti_sysbios_knl_Clock_tickMode;
extern  const CT__ti_sysbios_knl_Clock_tickMode ti_sysbios_knl_Clock_tickMode__C;

 
typedef xdc_UInt CT__ti_sysbios_knl_Clock_timerId;
extern  const CT__ti_sysbios_knl_Clock_timerId ti_sysbios_knl_Clock_timerId__C;

 
typedef xdc_UInt32 CT__ti_sysbios_knl_Clock_tickPeriod;
extern  const CT__ti_sysbios_knl_Clock_tickPeriod ti_sysbios_knl_Clock_tickPeriod__C;

 
typedef void (*CT__ti_sysbios_knl_Clock_doTickFunc)(xdc_UArg);
extern  const CT__ti_sysbios_knl_Clock_doTickFunc ti_sysbios_knl_Clock_doTickFunc__C;

 
typedef ti_sysbios_knl_Clock_Handle CT__ti_sysbios_knl_Clock_triggerClock;
extern  const CT__ti_sysbios_knl_Clock_triggerClock ti_sysbios_knl_Clock_triggerClock__C;




 

 
struct ti_sysbios_knl_Clock_Params {
    size_t __size;
    const void *__self;
    void *__fxns;
    xdc_runtime_IInstance_Params *instance;
    xdc_Bool startFlag;
    xdc_UInt32 period;
    xdc_UArg arg;
    xdc_runtime_IInstance_Params __iprms;
};

 
struct ti_sysbios_knl_Clock_Struct {
    ti_sysbios_knl_Queue_Elem __f0;
    xdc_UInt32 __f1;
    xdc_UInt32 __f2;
    xdc_UInt32 __f3;
    volatile xdc_Bool __f4;
    ti_sysbios_knl_Clock_FuncPtr __f5;
    xdc_UArg __f6;
    xdc_runtime_Types_CordAddr __name;
};




 

 

extern xdc_Int ti_sysbios_knl_Clock_Module_startup__E( xdc_Int state );

extern xdc_Int ti_sysbios_knl_Clock_Module_startup__F( xdc_Int state );

 

extern void ti_sysbios_knl_Clock_Instance_init__E(ti_sysbios_knl_Clock_Object *, ti_sysbios_knl_Clock_FuncPtr clockFxn, xdc_UInt timeout, const ti_sysbios_knl_Clock_Params *);

 

extern void ti_sysbios_knl_Clock_Instance_finalize__E( ti_sysbios_knl_Clock_Object* );

 

extern xdc_runtime_Types_Label *ti_sysbios_knl_Clock_Handle__label__S( xdc_Ptr obj, xdc_runtime_Types_Label *lab );

 

extern xdc_Bool ti_sysbios_knl_Clock_Module__startupDone__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Clock_Object__create__S( xdc_Ptr __oa, xdc_SizeT __osz, xdc_Ptr __aa, const xdc_UChar *__pa, xdc_SizeT __psz, xdc_runtime_Error_Block *__eb );

 

extern ti_sysbios_knl_Clock_Handle ti_sysbios_knl_Clock_create( ti_sysbios_knl_Clock_FuncPtr clockFxn, xdc_UInt timeout, const ti_sysbios_knl_Clock_Params *__prms, xdc_runtime_Error_Block *__eb );

 

extern void ti_sysbios_knl_Clock_construct( ti_sysbios_knl_Clock_Struct *__obj, ti_sysbios_knl_Clock_FuncPtr clockFxn, xdc_UInt timeout, const ti_sysbios_knl_Clock_Params *__prms );

 

extern void ti_sysbios_knl_Clock_Object__delete__S( xdc_Ptr instp );

 

extern void ti_sysbios_knl_Clock_delete(ti_sysbios_knl_Clock_Handle *instp);

 

extern void ti_sysbios_knl_Clock_Object__destruct__S( xdc_Ptr objp );

 

extern void ti_sysbios_knl_Clock_destruct(ti_sysbios_knl_Clock_Struct *obj);

 

extern xdc_Ptr ti_sysbios_knl_Clock_Object__get__S( xdc_Ptr oarr, xdc_Int i );

 

extern xdc_Ptr ti_sysbios_knl_Clock_Object__first__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Clock_Object__next__S( xdc_Ptr obj );

 

extern void ti_sysbios_knl_Clock_Params__init__S( xdc_Ptr dst, const void *src, xdc_SizeT psz, xdc_SizeT isz );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_getTicks__E( void );

 

extern ti_sysbios_knl_Clock_TimerProxy_Handle ti_sysbios_knl_Clock_getTimerHandle__E( void );

 

extern void ti_sysbios_knl_Clock_setTicks__E( xdc_UInt32 ticks );

 

extern void ti_sysbios_knl_Clock_tickStop__E( void );

 

extern xdc_Bool ti_sysbios_knl_Clock_tickReconfig__E( void );

 

extern void ti_sysbios_knl_Clock_tickStart__E( void );

 

extern void ti_sysbios_knl_Clock_tick__E( void );

 

extern void ti_sysbios_knl_Clock_workFunc__E( xdc_UArg arg0, xdc_UArg arg1 );

 

extern void ti_sysbios_knl_Clock_workFuncDynamic__E( xdc_UArg arg0, xdc_UArg arg1 );

 

extern void ti_sysbios_knl_Clock_logTick__E( void );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_getCompletedTicks__E( void );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_getTickPeriod__E( void );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_getTicksUntilInterrupt__E( void );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_getTicksUntilTimeout__E( void );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_walkQueueDynamic__E( xdc_Bool service, xdc_UInt32 tick );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_walkQueuePeriodic__E( void );

 

extern void ti_sysbios_knl_Clock_scheduleNextTick__E( xdc_UInt32 deltaTicks, xdc_UInt32 absTick );

 

extern void ti_sysbios_knl_Clock_addI__E( ti_sysbios_knl_Clock_Handle __inst, ti_sysbios_knl_Clock_FuncPtr clockFxn, xdc_UInt32 timeout, xdc_UArg arg );

 

extern void ti_sysbios_knl_Clock_removeI__E( ti_sysbios_knl_Clock_Handle __inst );

 

extern void ti_sysbios_knl_Clock_start__E( ti_sysbios_knl_Clock_Handle __inst );

 

extern void ti_sysbios_knl_Clock_startI__E( ti_sysbios_knl_Clock_Handle __inst );

 

extern void ti_sysbios_knl_Clock_stop__E( ti_sysbios_knl_Clock_Handle __inst );

 

extern void ti_sysbios_knl_Clock_setPeriod__E( ti_sysbios_knl_Clock_Handle __inst, xdc_UInt32 period );

 

extern void ti_sysbios_knl_Clock_setTimeout__E( ti_sysbios_knl_Clock_Handle __inst, xdc_UInt32 timeout );

 

extern void ti_sysbios_knl_Clock_setFunc__E( ti_sysbios_knl_Clock_Handle __inst, ti_sysbios_knl_Clock_FuncPtr fxn, xdc_UArg arg );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_getPeriod__E( ti_sysbios_knl_Clock_Handle __inst );

 

extern xdc_UInt32 ti_sysbios_knl_Clock_getTimeout__E( ti_sysbios_knl_Clock_Handle __inst );

 

extern xdc_Bool ti_sysbios_knl_Clock_isActive__E( ti_sysbios_knl_Clock_Handle __inst );

 

extern void ti_sysbios_knl_Clock_doTick__I( xdc_UArg arg );

 

extern void ti_sysbios_knl_Clock_triggerFunc__I( xdc_UArg arg );




 

 

 

 

 
static inline CT__ti_sysbios_knl_Clock_Module__id ti_sysbios_knl_Clock_Module_id( void ) 
{
    return ti_sysbios_knl_Clock_Module__id__C;
}

 
static inline xdc_Bool ti_sysbios_knl_Clock_Module_hasMask( void ) 
{
    return ti_sysbios_knl_Clock_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 ti_sysbios_knl_Clock_Module_getMask( void ) 
{
    return ti_sysbios_knl_Clock_Module__diagsMask__C != 0 ? *ti_sysbios_knl_Clock_Module__diagsMask__C : 0;
}

 
static inline void ti_sysbios_knl_Clock_Module_setMask( xdc_Bits16 mask ) 
{
    if (ti_sysbios_knl_Clock_Module__diagsMask__C != 0) *ti_sysbios_knl_Clock_Module__diagsMask__C = mask;
}

 
static inline void ti_sysbios_knl_Clock_Params_init( ti_sysbios_knl_Clock_Params *prms ) 
{
    if (prms) {
        ti_sysbios_knl_Clock_Params__init__S(prms, 0, sizeof(ti_sysbios_knl_Clock_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 
static inline void ti_sysbios_knl_Clock_Params_copy(ti_sysbios_knl_Clock_Params *dst, const ti_sysbios_knl_Clock_Params *src) 
{
    if (dst) {
        ti_sysbios_knl_Clock_Params__init__S(dst, (const void *)src, sizeof(ti_sysbios_knl_Clock_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 

 

 
static inline ti_sysbios_knl_Clock_Handle ti_sysbios_knl_Clock_Object_get(ti_sysbios_knl_Clock_Instance_State *oarr, int i) 
{
    return (ti_sysbios_knl_Clock_Handle)ti_sysbios_knl_Clock_Object__get__S(oarr, i);
}

 
static inline ti_sysbios_knl_Clock_Handle ti_sysbios_knl_Clock_Object_first( void )
{
    return (ti_sysbios_knl_Clock_Handle)ti_sysbios_knl_Clock_Object__first__S();
}

 
static inline ti_sysbios_knl_Clock_Handle ti_sysbios_knl_Clock_Object_next( ti_sysbios_knl_Clock_Object *obj )
{
    return (ti_sysbios_knl_Clock_Handle)ti_sysbios_knl_Clock_Object__next__S(obj);
}

 
static inline xdc_runtime_Types_Label *ti_sysbios_knl_Clock_Handle_label( ti_sysbios_knl_Clock_Handle inst, xdc_runtime_Types_Label *lab )
{
    return ti_sysbios_knl_Clock_Handle__label__S(inst, lab);
}

 
static inline xdc_String ti_sysbios_knl_Clock_Handle_name( ti_sysbios_knl_Clock_Handle inst )
{
    xdc_runtime_Types_Label lab;
    return ti_sysbios_knl_Clock_Handle__label__S(inst, &lab)->iname;
}

 
static inline ti_sysbios_knl_Clock_Handle ti_sysbios_knl_Clock_handle( ti_sysbios_knl_Clock_Struct *str )
{
    return (ti_sysbios_knl_Clock_Handle)str;
}

 
static inline ti_sysbios_knl_Clock_Struct *ti_sysbios_knl_Clock_struct( ti_sysbios_knl_Clock_Handle inst )
{
    return (ti_sysbios_knl_Clock_Struct*)inst;
}




 






 





 






 















 




 








 







 















 




 





 




 



 







 













 




 





 





 

 
typedef ti_sysbios_interfaces_ITaskSupport_FuncPtr ti_sysbios_knl_Task_SupportProxy_FuncPtr;




 

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Task_SupportProxy_Module__diagsEnabled;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Module__diagsEnabled ti_sysbios_knl_Task_SupportProxy_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Task_SupportProxy_Module__diagsIncluded;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Module__diagsIncluded ti_sysbios_knl_Task_SupportProxy_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__ti_sysbios_knl_Task_SupportProxy_Module__diagsMask;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Module__diagsMask ti_sysbios_knl_Task_SupportProxy_Module__diagsMask__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Task_SupportProxy_Module__gateObj;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Module__gateObj ti_sysbios_knl_Task_SupportProxy_Module__gateObj__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Task_SupportProxy_Module__gatePrms;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Module__gatePrms ti_sysbios_knl_Task_SupportProxy_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__ti_sysbios_knl_Task_SupportProxy_Module__id;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Module__id ti_sysbios_knl_Task_SupportProxy_Module__id__C;

 
typedef xdc_Bool CT__ti_sysbios_knl_Task_SupportProxy_Module__loggerDefined;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Module__loggerDefined ti_sysbios_knl_Task_SupportProxy_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Task_SupportProxy_Module__loggerObj;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Module__loggerObj ti_sysbios_knl_Task_SupportProxy_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn0;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn0 ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn1;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn1 ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn2;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn2 ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn4;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn4 ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn8;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn8 ti_sysbios_knl_Task_SupportProxy_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__ti_sysbios_knl_Task_SupportProxy_Module__startupDoneFxn)(void);
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Module__startupDoneFxn ti_sysbios_knl_Task_SupportProxy_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__ti_sysbios_knl_Task_SupportProxy_Object__count;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Object__count ti_sysbios_knl_Task_SupportProxy_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__ti_sysbios_knl_Task_SupportProxy_Object__heap;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Object__heap ti_sysbios_knl_Task_SupportProxy_Object__heap__C;

 
typedef xdc_SizeT CT__ti_sysbios_knl_Task_SupportProxy_Object__sizeof;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Object__sizeof ti_sysbios_knl_Task_SupportProxy_Object__sizeof__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Task_SupportProxy_Object__table;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_Object__table ti_sysbios_knl_Task_SupportProxy_Object__table__C;

 
typedef xdc_SizeT CT__ti_sysbios_knl_Task_SupportProxy_defaultStackSize;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_defaultStackSize ti_sysbios_knl_Task_SupportProxy_defaultStackSize__C;

 
typedef xdc_UInt CT__ti_sysbios_knl_Task_SupportProxy_stackAlignment;
extern  const CT__ti_sysbios_knl_Task_SupportProxy_stackAlignment ti_sysbios_knl_Task_SupportProxy_stackAlignment__C;




 

 
struct ti_sysbios_knl_Task_SupportProxy_Fxns__ {
    xdc_runtime_Types_Base* __base;
    const xdc_runtime_Types_SysFxns2 *__sysp;
    xdc_Ptr (*start)(xdc_Ptr, ti_sysbios_interfaces_ITaskSupport_FuncPtr, ti_sysbios_interfaces_ITaskSupport_FuncPtr, xdc_runtime_Error_Block*);
    void (*swap)(xdc_Ptr*, xdc_Ptr*);
    xdc_Bool (*checkStack)(xdc_Char*, xdc_SizeT);
    xdc_SizeT (*stackUsed)(xdc_Char*, xdc_SizeT);
    xdc_UInt (*getStackAlignment)(void);
    xdc_SizeT (*getDefaultStackSize)(void);
    xdc_runtime_Types_SysFxns2 __sfxns;
};

 
extern const ti_sysbios_knl_Task_SupportProxy_Fxns__ ti_sysbios_knl_Task_SupportProxy_Module__FXNS__C;




 

 

 

extern xdc_runtime_Types_Label *ti_sysbios_knl_Task_SupportProxy_Handle__label__S( xdc_Ptr obj, xdc_runtime_Types_Label *lab );

 

extern xdc_Bool ti_sysbios_knl_Task_SupportProxy_Module__startupDone__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Task_SupportProxy_Object__create__S( xdc_Ptr __oa, xdc_SizeT __osz, xdc_Ptr __aa, const xdc_UChar *__pa, xdc_SizeT __psz, xdc_runtime_Error_Block *__eb );

 

extern void ti_sysbios_knl_Task_SupportProxy_Object__delete__S( xdc_Ptr instp );

 

extern void ti_sysbios_knl_Task_SupportProxy_Object__destruct__S( xdc_Ptr objp );

 

extern xdc_Ptr ti_sysbios_knl_Task_SupportProxy_Object__get__S( xdc_Ptr oarr, xdc_Int i );

 

extern xdc_Ptr ti_sysbios_knl_Task_SupportProxy_Object__first__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Task_SupportProxy_Object__next__S( xdc_Ptr obj );

 

extern void ti_sysbios_knl_Task_SupportProxy_Params__init__S( xdc_Ptr dst, const void *src, xdc_SizeT psz, xdc_SizeT isz );

 

extern xdc_Bool ti_sysbios_knl_Task_SupportProxy_Proxy__abstract__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Task_SupportProxy_Proxy__delegate__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Task_SupportProxy_start__E( xdc_Ptr curTask, ti_sysbios_interfaces_ITaskSupport_FuncPtr enter, ti_sysbios_interfaces_ITaskSupport_FuncPtr exit, xdc_runtime_Error_Block *eb );

 

extern void ti_sysbios_knl_Task_SupportProxy_swap__E( xdc_Ptr *oldtskContext, xdc_Ptr *newtskContext );

 

extern xdc_Bool ti_sysbios_knl_Task_SupportProxy_checkStack__E( xdc_Char *stack, xdc_SizeT size );

 

extern xdc_SizeT ti_sysbios_knl_Task_SupportProxy_stackUsed__E( xdc_Char *stack, xdc_SizeT size );

 

extern xdc_UInt ti_sysbios_knl_Task_SupportProxy_getStackAlignment__E( void );

 

extern xdc_SizeT ti_sysbios_knl_Task_SupportProxy_getDefaultStackSize__E( void );




 

 
static inline ti_sysbios_interfaces_ITaskSupport_Module ti_sysbios_knl_Task_SupportProxy_Module_upCast( void )
{
    return (ti_sysbios_interfaces_ITaskSupport_Module)ti_sysbios_knl_Task_SupportProxy_Proxy__delegate__S();
}

 




 

 

 

 

 
static inline CT__ti_sysbios_knl_Task_SupportProxy_Module__id ti_sysbios_knl_Task_SupportProxy_Module_id( void ) 
{
    return ti_sysbios_knl_Task_SupportProxy_Module__id__C;
}

 

 




 






 





 

 
typedef void (*ti_sysbios_knl_Task_FuncPtr)(xdc_UArg, xdc_UArg);

 
typedef void (*ti_sysbios_knl_Task_AllBlockedFuncPtr)(void);

 
enum ti_sysbios_knl_Task_Mode {
    ti_sysbios_knl_Task_Mode_RUNNING,
    ti_sysbios_knl_Task_Mode_READY,
    ti_sysbios_knl_Task_Mode_BLOCKED,
    ti_sysbios_knl_Task_Mode_TERMINATED,
    ti_sysbios_knl_Task_Mode_INACTIVE
};
typedef enum ti_sysbios_knl_Task_Mode ti_sysbios_knl_Task_Mode;

 
struct ti_sysbios_knl_Task_Stat {
    xdc_Int priority;
    xdc_Ptr stack;
    xdc_SizeT stackSize;
    xdc_runtime_IHeap_Handle stackHeap;
    xdc_Ptr env;
    ti_sysbios_knl_Task_Mode mode;
    xdc_Ptr sp;
    xdc_SizeT used;
};

 
struct ti_sysbios_knl_Task_HookSet {
    void (*registerFxn)(xdc_Int);
    void (*createFxn)(ti_sysbios_knl_Task_Handle, xdc_runtime_Error_Block*);
    void (*readyFxn)(ti_sysbios_knl_Task_Handle);
    void (*switchFxn)(ti_sysbios_knl_Task_Handle, ti_sysbios_knl_Task_Handle);
    void (*exitFxn)(ti_sysbios_knl_Task_Handle);
    void (*deleteFxn)(ti_sysbios_knl_Task_Handle);
};

 




 

 
typedef struct ti_sysbios_knl_Task_Args__create {
    ti_sysbios_knl_Task_FuncPtr fxn;
} ti_sysbios_knl_Task_Args__create;




 

 
struct ti_sysbios_knl_Task_PendElem {
    ti_sysbios_knl_Queue_Elem qElem;
    ti_sysbios_knl_Task_Handle task;
    ti_sysbios_knl_Clock_Handle clock;
};

 
typedef xdc_Char __T1_ti_sysbios_knl_Task_Instance_State__stack;
typedef xdc_Char *__ARRAY1_ti_sysbios_knl_Task_Instance_State__stack;
typedef __ARRAY1_ti_sysbios_knl_Task_Instance_State__stack __TA_ti_sysbios_knl_Task_Instance_State__stack;
typedef xdc_Ptr __T1_ti_sysbios_knl_Task_Instance_State__hookEnv;
typedef xdc_Ptr *__ARRAY1_ti_sysbios_knl_Task_Instance_State__hookEnv;
typedef __ARRAY1_ti_sysbios_knl_Task_Instance_State__hookEnv __TA_ti_sysbios_knl_Task_Instance_State__hookEnv;

 
typedef ti_sysbios_knl_Queue_Instance_State __T1_ti_sysbios_knl_Task_Module_State__readyQ;
typedef ti_sysbios_knl_Queue_Instance_State *__ARRAY1_ti_sysbios_knl_Task_Module_State__readyQ;
typedef __ARRAY1_ti_sysbios_knl_Task_Module_State__readyQ __TA_ti_sysbios_knl_Task_Module_State__readyQ;
typedef volatile xdc_UInt __T1_ti_sysbios_knl_Task_Module_State__smpCurSet;
typedef volatile xdc_UInt *__ARRAY1_ti_sysbios_knl_Task_Module_State__smpCurSet;
typedef __ARRAY1_ti_sysbios_knl_Task_Module_State__smpCurSet __TA_ti_sysbios_knl_Task_Module_State__smpCurSet;
typedef volatile xdc_UInt __T1_ti_sysbios_knl_Task_Module_State__smpCurMask;
typedef volatile xdc_UInt *__ARRAY1_ti_sysbios_knl_Task_Module_State__smpCurMask;
typedef __ARRAY1_ti_sysbios_knl_Task_Module_State__smpCurMask __TA_ti_sysbios_knl_Task_Module_State__smpCurMask;
typedef ti_sysbios_knl_Task_Handle __T1_ti_sysbios_knl_Task_Module_State__smpCurTask;
typedef ti_sysbios_knl_Task_Handle *__ARRAY1_ti_sysbios_knl_Task_Module_State__smpCurTask;
typedef __ARRAY1_ti_sysbios_knl_Task_Module_State__smpCurTask __TA_ti_sysbios_knl_Task_Module_State__smpCurTask;
typedef ti_sysbios_knl_Queue_Handle __T1_ti_sysbios_knl_Task_Module_State__smpReadyQ;
typedef ti_sysbios_knl_Queue_Handle *__ARRAY1_ti_sysbios_knl_Task_Module_State__smpReadyQ;
typedef __ARRAY1_ti_sysbios_knl_Task_Module_State__smpReadyQ __TA_ti_sysbios_knl_Task_Module_State__smpReadyQ;
typedef ti_sysbios_knl_Task_Handle __T1_ti_sysbios_knl_Task_Module_State__idleTask;
typedef ti_sysbios_knl_Task_Handle *__ARRAY1_ti_sysbios_knl_Task_Module_State__idleTask;
typedef __ARRAY1_ti_sysbios_knl_Task_Module_State__idleTask __TA_ti_sysbios_knl_Task_Module_State__idleTask;
typedef ti_sysbios_knl_Task_Handle __T1_ti_sysbios_knl_Task_Module_State__constructedTasks;
typedef ti_sysbios_knl_Task_Handle *__ARRAY1_ti_sysbios_knl_Task_Module_State__constructedTasks;
typedef __ARRAY1_ti_sysbios_knl_Task_Module_State__constructedTasks __TA_ti_sysbios_knl_Task_Module_State__constructedTasks;

 
struct ti_sysbios_knl_Task_RunQEntry {
    ti_sysbios_knl_Queue_Elem elem;
    xdc_UInt coreId;
    xdc_Int priority;
};

 
typedef volatile ti_sysbios_knl_Task_RunQEntry __T1_ti_sysbios_knl_Task_Module_StateSmp__smpRunQ;
typedef volatile ti_sysbios_knl_Task_RunQEntry *__ARRAY1_ti_sysbios_knl_Task_Module_StateSmp__smpRunQ;
typedef __ARRAY1_ti_sysbios_knl_Task_Module_StateSmp__smpRunQ __TA_ti_sysbios_knl_Task_Module_StateSmp__smpRunQ;
struct ti_sysbios_knl_Task_Module_StateSmp {
    ti_sysbios_knl_Queue_Object *sortedRunQ;
    __TA_ti_sysbios_knl_Task_Module_StateSmp__smpRunQ smpRunQ;
};




 

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Task_Module__diagsEnabled;
extern  const CT__ti_sysbios_knl_Task_Module__diagsEnabled ti_sysbios_knl_Task_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Task_Module__diagsIncluded;
extern  const CT__ti_sysbios_knl_Task_Module__diagsIncluded ti_sysbios_knl_Task_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__ti_sysbios_knl_Task_Module__diagsMask;
extern  const CT__ti_sysbios_knl_Task_Module__diagsMask ti_sysbios_knl_Task_Module__diagsMask__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Task_Module__gateObj;
extern  const CT__ti_sysbios_knl_Task_Module__gateObj ti_sysbios_knl_Task_Module__gateObj__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Task_Module__gatePrms;
extern  const CT__ti_sysbios_knl_Task_Module__gatePrms ti_sysbios_knl_Task_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__ti_sysbios_knl_Task_Module__id;
extern  const CT__ti_sysbios_knl_Task_Module__id ti_sysbios_knl_Task_Module__id__C;

 
typedef xdc_Bool CT__ti_sysbios_knl_Task_Module__loggerDefined;
extern  const CT__ti_sysbios_knl_Task_Module__loggerDefined ti_sysbios_knl_Task_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Task_Module__loggerObj;
extern  const CT__ti_sysbios_knl_Task_Module__loggerObj ti_sysbios_knl_Task_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__ti_sysbios_knl_Task_Module__loggerFxn0;
extern  const CT__ti_sysbios_knl_Task_Module__loggerFxn0 ti_sysbios_knl_Task_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__ti_sysbios_knl_Task_Module__loggerFxn1;
extern  const CT__ti_sysbios_knl_Task_Module__loggerFxn1 ti_sysbios_knl_Task_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__ti_sysbios_knl_Task_Module__loggerFxn2;
extern  const CT__ti_sysbios_knl_Task_Module__loggerFxn2 ti_sysbios_knl_Task_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__ti_sysbios_knl_Task_Module__loggerFxn4;
extern  const CT__ti_sysbios_knl_Task_Module__loggerFxn4 ti_sysbios_knl_Task_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__ti_sysbios_knl_Task_Module__loggerFxn8;
extern  const CT__ti_sysbios_knl_Task_Module__loggerFxn8 ti_sysbios_knl_Task_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__ti_sysbios_knl_Task_Module__startupDoneFxn)(void);
extern  const CT__ti_sysbios_knl_Task_Module__startupDoneFxn ti_sysbios_knl_Task_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__ti_sysbios_knl_Task_Object__count;
extern  const CT__ti_sysbios_knl_Task_Object__count ti_sysbios_knl_Task_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__ti_sysbios_knl_Task_Object__heap;
extern  const CT__ti_sysbios_knl_Task_Object__heap ti_sysbios_knl_Task_Object__heap__C;

 
typedef xdc_SizeT CT__ti_sysbios_knl_Task_Object__sizeof;
extern  const CT__ti_sysbios_knl_Task_Object__sizeof ti_sysbios_knl_Task_Object__sizeof__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Task_Object__table;
extern  const CT__ti_sysbios_knl_Task_Object__table ti_sysbios_knl_Task_Object__table__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Task_LM_switch;
extern  const CT__ti_sysbios_knl_Task_LM_switch ti_sysbios_knl_Task_LM_switch__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Task_LM_sleep;
extern  const CT__ti_sysbios_knl_Task_LM_sleep ti_sysbios_knl_Task_LM_sleep__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Task_LD_ready;
extern  const CT__ti_sysbios_knl_Task_LD_ready ti_sysbios_knl_Task_LD_ready__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Task_LD_block;
extern  const CT__ti_sysbios_knl_Task_LD_block ti_sysbios_knl_Task_LD_block__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Task_LM_yield;
extern  const CT__ti_sysbios_knl_Task_LM_yield ti_sysbios_knl_Task_LM_yield__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Task_LM_setPri;
extern  const CT__ti_sysbios_knl_Task_LM_setPri ti_sysbios_knl_Task_LM_setPri__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Task_LD_exit;
extern  const CT__ti_sysbios_knl_Task_LD_exit ti_sysbios_knl_Task_LD_exit__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Task_LM_setAffinity;
extern  const CT__ti_sysbios_knl_Task_LM_setAffinity ti_sysbios_knl_Task_LM_setAffinity__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Task_LM_schedule;
extern  const CT__ti_sysbios_knl_Task_LM_schedule ti_sysbios_knl_Task_LM_schedule__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Task_LM_noWork;
extern  const CT__ti_sysbios_knl_Task_LM_noWork ti_sysbios_knl_Task_LM_noWork__C;

 
typedef xdc_runtime_Error_Id CT__ti_sysbios_knl_Task_E_stackOverflow;
extern  const CT__ti_sysbios_knl_Task_E_stackOverflow ti_sysbios_knl_Task_E_stackOverflow__C;

 
typedef xdc_runtime_Error_Id CT__ti_sysbios_knl_Task_E_spOutOfBounds;
extern  const CT__ti_sysbios_knl_Task_E_spOutOfBounds ti_sysbios_knl_Task_E_spOutOfBounds__C;

 
typedef xdc_runtime_Error_Id CT__ti_sysbios_knl_Task_E_deleteNotAllowed;
extern  const CT__ti_sysbios_knl_Task_E_deleteNotAllowed ti_sysbios_knl_Task_E_deleteNotAllowed__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Task_A_badThreadType;
extern  const CT__ti_sysbios_knl_Task_A_badThreadType ti_sysbios_knl_Task_A_badThreadType__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Task_A_badTaskState;
extern  const CT__ti_sysbios_knl_Task_A_badTaskState ti_sysbios_knl_Task_A_badTaskState__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Task_A_noPendElem;
extern  const CT__ti_sysbios_knl_Task_A_noPendElem ti_sysbios_knl_Task_A_noPendElem__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Task_A_taskDisabled;
extern  const CT__ti_sysbios_knl_Task_A_taskDisabled ti_sysbios_knl_Task_A_taskDisabled__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Task_A_badPriority;
extern  const CT__ti_sysbios_knl_Task_A_badPriority ti_sysbios_knl_Task_A_badPriority__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Task_A_badTimeout;
extern  const CT__ti_sysbios_knl_Task_A_badTimeout ti_sysbios_knl_Task_A_badTimeout__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Task_A_badAffinity;
extern  const CT__ti_sysbios_knl_Task_A_badAffinity ti_sysbios_knl_Task_A_badAffinity__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Task_A_sleepTaskDisabled;
extern  const CT__ti_sysbios_knl_Task_A_sleepTaskDisabled ti_sysbios_knl_Task_A_sleepTaskDisabled__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Task_A_invalidCoreId;
extern  const CT__ti_sysbios_knl_Task_A_invalidCoreId ti_sysbios_knl_Task_A_invalidCoreId__C;

 
typedef xdc_UInt CT__ti_sysbios_knl_Task_numPriorities;
extern  const CT__ti_sysbios_knl_Task_numPriorities ti_sysbios_knl_Task_numPriorities__C;

 
typedef xdc_SizeT CT__ti_sysbios_knl_Task_defaultStackSize;
extern  const CT__ti_sysbios_knl_Task_defaultStackSize ti_sysbios_knl_Task_defaultStackSize__C;

 
typedef xdc_runtime_IHeap_Handle CT__ti_sysbios_knl_Task_defaultStackHeap;
extern  const CT__ti_sysbios_knl_Task_defaultStackHeap ti_sysbios_knl_Task_defaultStackHeap__C;

 
typedef ti_sysbios_knl_Task_AllBlockedFuncPtr CT__ti_sysbios_knl_Task_allBlockedFunc;
extern  const CT__ti_sysbios_knl_Task_allBlockedFunc ti_sysbios_knl_Task_allBlockedFunc__C;

 
typedef xdc_Bool CT__ti_sysbios_knl_Task_initStackFlag;
extern  const CT__ti_sysbios_knl_Task_initStackFlag ti_sysbios_knl_Task_initStackFlag__C;

 
typedef xdc_Bool CT__ti_sysbios_knl_Task_checkStackFlag;
extern  const CT__ti_sysbios_knl_Task_checkStackFlag ti_sysbios_knl_Task_checkStackFlag__C;

 
typedef xdc_Bool CT__ti_sysbios_knl_Task_deleteTerminatedTasks;
extern  const CT__ti_sysbios_knl_Task_deleteTerminatedTasks ti_sysbios_knl_Task_deleteTerminatedTasks__C;

 
typedef ti_sysbios_knl_Task_HookSet __T1_ti_sysbios_knl_Task_hooks;
typedef struct { int length; ti_sysbios_knl_Task_HookSet *elem; } __ARRAY1_ti_sysbios_knl_Task_hooks;
typedef __ARRAY1_ti_sysbios_knl_Task_hooks __TA_ti_sysbios_knl_Task_hooks;
typedef __TA_ti_sysbios_knl_Task_hooks CT__ti_sysbios_knl_Task_hooks;
extern  const CT__ti_sysbios_knl_Task_hooks ti_sysbios_knl_Task_hooks__C;

 
typedef xdc_UInt CT__ti_sysbios_knl_Task_numConstructedTasks;
extern  const CT__ti_sysbios_knl_Task_numConstructedTasks ti_sysbios_knl_Task_numConstructedTasks__C;

 
typedef void (*CT__ti_sysbios_knl_Task_startupHookFunc)(void);
extern  const CT__ti_sysbios_knl_Task_startupHookFunc ti_sysbios_knl_Task_startupHookFunc__C;




 

 
struct ti_sysbios_knl_Task_Params {
    size_t __size;
    const void *__self;
    void *__fxns;
    xdc_runtime_IInstance_Params *instance;
    xdc_UArg arg0;
    xdc_UArg arg1;
    xdc_Int priority;
    xdc_Ptr stack;
    xdc_SizeT stackSize;
    xdc_runtime_IHeap_Handle stackHeap;
    xdc_Ptr env;
    xdc_Bool vitalTaskFlag;
    xdc_UInt affinity;
    xdc_runtime_IInstance_Params __iprms;
};

 
struct ti_sysbios_knl_Task_Struct {
    ti_sysbios_knl_Queue_Elem __f0;
    volatile xdc_Int __f1;
    xdc_UInt __f2;
    xdc_Ptr __f3;
    ti_sysbios_knl_Task_Mode __f4;
    ti_sysbios_knl_Task_PendElem *__f5;
    xdc_SizeT __f6;
    __TA_ti_sysbios_knl_Task_Instance_State__stack __f7;
    xdc_runtime_IHeap_Handle __f8;
    ti_sysbios_knl_Task_FuncPtr __f9;
    xdc_UArg __f10;
    xdc_UArg __f11;
    xdc_Ptr __f12;
    __TA_ti_sysbios_knl_Task_Instance_State__hookEnv __f13;
    xdc_Bool __f14;
    ti_sysbios_knl_Queue_Handle __f15;
    xdc_UInt __f16;
    xdc_UInt __f17;
    xdc_runtime_Types_CordAddr __name;
};




 

 

extern xdc_Int ti_sysbios_knl_Task_Module_startup__E( xdc_Int state );

extern xdc_Int ti_sysbios_knl_Task_Module_startup__F( xdc_Int state );

 

extern xdc_Int ti_sysbios_knl_Task_Instance_init__E(ti_sysbios_knl_Task_Object *, ti_sysbios_knl_Task_FuncPtr fxn, const ti_sysbios_knl_Task_Params *, xdc_runtime_Error_Block *);

 

extern void ti_sysbios_knl_Task_Instance_finalize__E( ti_sysbios_knl_Task_Object* , int );

 

extern xdc_runtime_Types_Label *ti_sysbios_knl_Task_Handle__label__S( xdc_Ptr obj, xdc_runtime_Types_Label *lab );

 

extern xdc_Bool ti_sysbios_knl_Task_Module__startupDone__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Task_Object__create__S( xdc_Ptr __oa, xdc_SizeT __osz, xdc_Ptr __aa, const xdc_UChar *__pa, xdc_SizeT __psz, xdc_runtime_Error_Block *__eb );

 

extern ti_sysbios_knl_Task_Handle ti_sysbios_knl_Task_create( ti_sysbios_knl_Task_FuncPtr fxn, const ti_sysbios_knl_Task_Params *__prms, xdc_runtime_Error_Block *__eb );

 

extern void ti_sysbios_knl_Task_construct( ti_sysbios_knl_Task_Struct *__obj, ti_sysbios_knl_Task_FuncPtr fxn, const ti_sysbios_knl_Task_Params *__prms, xdc_runtime_Error_Block *__eb );

 

extern void ti_sysbios_knl_Task_Object__delete__S( xdc_Ptr instp );

 

extern void ti_sysbios_knl_Task_delete(ti_sysbios_knl_Task_Handle *instp);

 

extern void ti_sysbios_knl_Task_Object__destruct__S( xdc_Ptr objp );

 

extern void ti_sysbios_knl_Task_destruct(ti_sysbios_knl_Task_Struct *obj);

 

extern xdc_Ptr ti_sysbios_knl_Task_Object__get__S( xdc_Ptr oarr, xdc_Int i );

 

extern xdc_Ptr ti_sysbios_knl_Task_Object__first__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Task_Object__next__S( xdc_Ptr obj );

 

extern void ti_sysbios_knl_Task_Params__init__S( xdc_Ptr dst, const void *src, xdc_SizeT psz, xdc_SizeT isz );

 

extern void ti_sysbios_knl_Task_startup__E( void );

 

extern xdc_Bool ti_sysbios_knl_Task_enabled__E( void );

 

extern void ti_sysbios_knl_Task_unlockSched__E( void );

 

extern xdc_UInt ti_sysbios_knl_Task_disable__E( void );

 

extern void ti_sysbios_knl_Task_enable__E( void );

 

extern void ti_sysbios_knl_Task_restore__E( xdc_UInt key );

 

extern void ti_sysbios_knl_Task_restoreHwi__E( xdc_UInt key );

 

extern ti_sysbios_knl_Task_Handle ti_sysbios_knl_Task_self__E( void );

 

extern void ti_sysbios_knl_Task_checkStacks__E( ti_sysbios_knl_Task_Handle oldTask, ti_sysbios_knl_Task_Handle newTask );

 

extern void ti_sysbios_knl_Task_exit__E( void );

 

extern void ti_sysbios_knl_Task_sleep__E( xdc_UInt32 nticks );

 

extern void ti_sysbios_knl_Task_yield__E( void );

 

extern ti_sysbios_knl_Task_Handle ti_sysbios_knl_Task_getIdleTask__E( void );

 

extern ti_sysbios_knl_Task_Handle ti_sysbios_knl_Task_getIdleTaskHandle__E( xdc_UInt coreId );

 

extern void ti_sysbios_knl_Task_startCore__E( xdc_UInt coreId );

 

extern xdc_UArg ti_sysbios_knl_Task_getArg0__E( ti_sysbios_knl_Task_Handle __inst );

 

extern xdc_UArg ti_sysbios_knl_Task_getArg1__E( ti_sysbios_knl_Task_Handle __inst );

 

extern xdc_Ptr ti_sysbios_knl_Task_getEnv__E( ti_sysbios_knl_Task_Handle __inst );

 

extern ti_sysbios_knl_Task_FuncPtr ti_sysbios_knl_Task_getFunc__E( ti_sysbios_knl_Task_Handle __inst, xdc_UArg *arg0, xdc_UArg *arg1 );

 

extern xdc_Ptr ti_sysbios_knl_Task_getHookContext__E( ti_sysbios_knl_Task_Handle __inst, xdc_Int id );

 

extern xdc_Int ti_sysbios_knl_Task_getPri__E( ti_sysbios_knl_Task_Handle __inst );

 

extern void ti_sysbios_knl_Task_setArg0__E( ti_sysbios_knl_Task_Handle __inst, xdc_UArg arg );

 

extern void ti_sysbios_knl_Task_setArg1__E( ti_sysbios_knl_Task_Handle __inst, xdc_UArg arg );

 

extern void ti_sysbios_knl_Task_setEnv__E( ti_sysbios_knl_Task_Handle __inst, xdc_Ptr env );

 

extern void ti_sysbios_knl_Task_setHookContext__E( ti_sysbios_knl_Task_Handle __inst, xdc_Int id, xdc_Ptr hookContext );

 

extern xdc_UInt ti_sysbios_knl_Task_setPri__E( ti_sysbios_knl_Task_Handle __inst, xdc_Int newpri );

 

extern void ti_sysbios_knl_Task_stat__E( ti_sysbios_knl_Task_Handle __inst, ti_sysbios_knl_Task_Stat *statbuf );

 

extern ti_sysbios_knl_Task_Mode ti_sysbios_knl_Task_getMode__E( ti_sysbios_knl_Task_Handle __inst );

 

extern xdc_UInt ti_sysbios_knl_Task_setAffinity__E( ti_sysbios_knl_Task_Handle __inst, xdc_UInt coreId );

 

extern xdc_UInt ti_sysbios_knl_Task_getAffinity__E( ti_sysbios_knl_Task_Handle __inst );

 

extern void ti_sysbios_knl_Task_block__E( ti_sysbios_knl_Task_Handle __inst );

 

extern void ti_sysbios_knl_Task_unblock__E( ti_sysbios_knl_Task_Handle __inst );

 

extern void ti_sysbios_knl_Task_blockI__E( ti_sysbios_knl_Task_Handle __inst );

 

extern void ti_sysbios_knl_Task_unblockI__E( ti_sysbios_knl_Task_Handle __inst, xdc_UInt hwiKey );

 

extern void ti_sysbios_knl_Task_schedule__I( void );

 

extern void ti_sysbios_knl_Task_enter__I( void );

 

extern void ti_sysbios_knl_Task_sleepTimeout__I( xdc_UArg arg );

 

extern xdc_Int ti_sysbios_knl_Task_postInit__I( ti_sysbios_knl_Task_Object *task, xdc_runtime_Error_Block *eb );

 

extern void ti_sysbios_knl_Task_allBlockedFunction__I( void );

 

extern void ti_sysbios_knl_Task_deleteTerminatedTasksFunc__I( void );

 

extern void ti_sysbios_knl_Task_processVitalTaskFlag__I( ti_sysbios_knl_Task_Object *task );




 

 

 

 

 
static inline CT__ti_sysbios_knl_Task_Module__id ti_sysbios_knl_Task_Module_id( void ) 
{
    return ti_sysbios_knl_Task_Module__id__C;
}

 
static inline xdc_Bool ti_sysbios_knl_Task_Module_hasMask( void ) 
{
    return ti_sysbios_knl_Task_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 ti_sysbios_knl_Task_Module_getMask( void ) 
{
    return ti_sysbios_knl_Task_Module__diagsMask__C != 0 ? *ti_sysbios_knl_Task_Module__diagsMask__C : 0;
}

 
static inline void ti_sysbios_knl_Task_Module_setMask( xdc_Bits16 mask ) 
{
    if (ti_sysbios_knl_Task_Module__diagsMask__C != 0) *ti_sysbios_knl_Task_Module__diagsMask__C = mask;
}

 
static inline void ti_sysbios_knl_Task_Params_init( ti_sysbios_knl_Task_Params *prms ) 
{
    if (prms) {
        ti_sysbios_knl_Task_Params__init__S(prms, 0, sizeof(ti_sysbios_knl_Task_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 
static inline void ti_sysbios_knl_Task_Params_copy(ti_sysbios_knl_Task_Params *dst, const ti_sysbios_knl_Task_Params *src) 
{
    if (dst) {
        ti_sysbios_knl_Task_Params__init__S(dst, (const void *)src, sizeof(ti_sysbios_knl_Task_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 

 

 
static inline ti_sysbios_knl_Task_Handle ti_sysbios_knl_Task_Object_get(ti_sysbios_knl_Task_Instance_State *oarr, int i) 
{
    return (ti_sysbios_knl_Task_Handle)ti_sysbios_knl_Task_Object__get__S(oarr, i);
}

 
static inline ti_sysbios_knl_Task_Handle ti_sysbios_knl_Task_Object_first( void )
{
    return (ti_sysbios_knl_Task_Handle)ti_sysbios_knl_Task_Object__first__S();
}

 
static inline ti_sysbios_knl_Task_Handle ti_sysbios_knl_Task_Object_next( ti_sysbios_knl_Task_Object *obj )
{
    return (ti_sysbios_knl_Task_Handle)ti_sysbios_knl_Task_Object__next__S(obj);
}

 
static inline xdc_runtime_Types_Label *ti_sysbios_knl_Task_Handle_label( ti_sysbios_knl_Task_Handle inst, xdc_runtime_Types_Label *lab )
{
    return ti_sysbios_knl_Task_Handle__label__S(inst, lab);
}

 
static inline xdc_String ti_sysbios_knl_Task_Handle_name( ti_sysbios_knl_Task_Handle inst )
{
    xdc_runtime_Types_Label lab;
    return ti_sysbios_knl_Task_Handle__label__S(inst, &lab)->iname;
}

 
static inline ti_sysbios_knl_Task_Handle ti_sysbios_knl_Task_handle( ti_sysbios_knl_Task_Struct *str )
{
    return (ti_sysbios_knl_Task_Handle)str;
}

 
static inline ti_sysbios_knl_Task_Struct *ti_sysbios_knl_Task_struct( ti_sysbios_knl_Task_Handle inst )
{
    return (ti_sysbios_knl_Task_Struct*)inst;
}




 































 




 








 





 






 

















 




 





 





 






 













 




 





 



 






 
















 




 








 







 















 




 





 




 



 






 














 




 





 



 































 






 















 




 





 




 



 






 















 




 





 




 



 






 















 




 





 




 



 






 
















 




 





 





 






 

















 




 





 





 






 

















 




 





 





 






 













 




 





 



 





 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 




 

 
enum ti_sysbios_knl_Event_PendState {
    ti_sysbios_knl_Event_PendState_TIMEOUT = 0,
    ti_sysbios_knl_Event_PendState_POSTED = 1,
    ti_sysbios_knl_Event_PendState_CLOCK_WAIT = 2,
    ti_sysbios_knl_Event_PendState_WAIT_FOREVER = 3
};
typedef enum ti_sysbios_knl_Event_PendState ti_sysbios_knl_Event_PendState;

 
struct ti_sysbios_knl_Event_PendElem {
    ti_sysbios_knl_Task_PendElem tpElem;
    ti_sysbios_knl_Event_PendState pendState;
    xdc_UInt matchingEvents;
    xdc_UInt andMask;
    xdc_UInt orMask;
};




 

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Event_Module__diagsEnabled;
extern  const CT__ti_sysbios_knl_Event_Module__diagsEnabled ti_sysbios_knl_Event_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Event_Module__diagsIncluded;
extern  const CT__ti_sysbios_knl_Event_Module__diagsIncluded ti_sysbios_knl_Event_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__ti_sysbios_knl_Event_Module__diagsMask;
extern  const CT__ti_sysbios_knl_Event_Module__diagsMask ti_sysbios_knl_Event_Module__diagsMask__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Event_Module__gateObj;
extern  const CT__ti_sysbios_knl_Event_Module__gateObj ti_sysbios_knl_Event_Module__gateObj__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Event_Module__gatePrms;
extern  const CT__ti_sysbios_knl_Event_Module__gatePrms ti_sysbios_knl_Event_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__ti_sysbios_knl_Event_Module__id;
extern  const CT__ti_sysbios_knl_Event_Module__id ti_sysbios_knl_Event_Module__id__C;

 
typedef xdc_Bool CT__ti_sysbios_knl_Event_Module__loggerDefined;
extern  const CT__ti_sysbios_knl_Event_Module__loggerDefined ti_sysbios_knl_Event_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Event_Module__loggerObj;
extern  const CT__ti_sysbios_knl_Event_Module__loggerObj ti_sysbios_knl_Event_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__ti_sysbios_knl_Event_Module__loggerFxn0;
extern  const CT__ti_sysbios_knl_Event_Module__loggerFxn0 ti_sysbios_knl_Event_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__ti_sysbios_knl_Event_Module__loggerFxn1;
extern  const CT__ti_sysbios_knl_Event_Module__loggerFxn1 ti_sysbios_knl_Event_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__ti_sysbios_knl_Event_Module__loggerFxn2;
extern  const CT__ti_sysbios_knl_Event_Module__loggerFxn2 ti_sysbios_knl_Event_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__ti_sysbios_knl_Event_Module__loggerFxn4;
extern  const CT__ti_sysbios_knl_Event_Module__loggerFxn4 ti_sysbios_knl_Event_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__ti_sysbios_knl_Event_Module__loggerFxn8;
extern  const CT__ti_sysbios_knl_Event_Module__loggerFxn8 ti_sysbios_knl_Event_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__ti_sysbios_knl_Event_Module__startupDoneFxn)(void);
extern  const CT__ti_sysbios_knl_Event_Module__startupDoneFxn ti_sysbios_knl_Event_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__ti_sysbios_knl_Event_Object__count;
extern  const CT__ti_sysbios_knl_Event_Object__count ti_sysbios_knl_Event_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__ti_sysbios_knl_Event_Object__heap;
extern  const CT__ti_sysbios_knl_Event_Object__heap ti_sysbios_knl_Event_Object__heap__C;

 
typedef xdc_SizeT CT__ti_sysbios_knl_Event_Object__sizeof;
extern  const CT__ti_sysbios_knl_Event_Object__sizeof ti_sysbios_knl_Event_Object__sizeof__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Event_Object__table;
extern  const CT__ti_sysbios_knl_Event_Object__table ti_sysbios_knl_Event_Object__table__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Event_LM_post;
extern  const CT__ti_sysbios_knl_Event_LM_post ti_sysbios_knl_Event_LM_post__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Event_LM_pend;
extern  const CT__ti_sysbios_knl_Event_LM_pend ti_sysbios_knl_Event_LM_pend__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Event_A_nullEventMasks;
extern  const CT__ti_sysbios_knl_Event_A_nullEventMasks ti_sysbios_knl_Event_A_nullEventMasks__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Event_A_nullEventId;
extern  const CT__ti_sysbios_knl_Event_A_nullEventId ti_sysbios_knl_Event_A_nullEventId__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Event_A_eventInUse;
extern  const CT__ti_sysbios_knl_Event_A_eventInUse ti_sysbios_knl_Event_A_eventInUse__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Event_A_badContext;
extern  const CT__ti_sysbios_knl_Event_A_badContext ti_sysbios_knl_Event_A_badContext__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Event_A_pendTaskDisabled;
extern  const CT__ti_sysbios_knl_Event_A_pendTaskDisabled ti_sysbios_knl_Event_A_pendTaskDisabled__C;




 

 
struct ti_sysbios_knl_Event_Params {
    size_t __size;
    const void *__self;
    void *__fxns;
    xdc_runtime_IInstance_Params *instance;
    xdc_runtime_IInstance_Params __iprms;
};

 
struct ti_sysbios_knl_Event_Struct {
    volatile xdc_UInt __f0;
    ti_sysbios_knl_Queue_Struct __f1;
    xdc_runtime_Types_CordAddr __name;
};




 

 

 

extern void ti_sysbios_knl_Event_Instance_init__E(ti_sysbios_knl_Event_Object *, const ti_sysbios_knl_Event_Params *);

 

extern xdc_runtime_Types_Label *ti_sysbios_knl_Event_Handle__label__S( xdc_Ptr obj, xdc_runtime_Types_Label *lab );

 

extern xdc_Bool ti_sysbios_knl_Event_Module__startupDone__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Event_Object__create__S( xdc_Ptr __oa, xdc_SizeT __osz, xdc_Ptr __aa, const xdc_UChar *__pa, xdc_SizeT __psz, xdc_runtime_Error_Block *__eb );

 

extern ti_sysbios_knl_Event_Handle ti_sysbios_knl_Event_create( const ti_sysbios_knl_Event_Params *__prms, xdc_runtime_Error_Block *__eb );

 

extern void ti_sysbios_knl_Event_construct( ti_sysbios_knl_Event_Struct *__obj, const ti_sysbios_knl_Event_Params *__prms );

 

extern void ti_sysbios_knl_Event_Object__delete__S( xdc_Ptr instp );

 

extern void ti_sysbios_knl_Event_delete(ti_sysbios_knl_Event_Handle *instp);

 

extern void ti_sysbios_knl_Event_Object__destruct__S( xdc_Ptr objp );

 

extern void ti_sysbios_knl_Event_destruct(ti_sysbios_knl_Event_Struct *obj);

 

extern xdc_Ptr ti_sysbios_knl_Event_Object__get__S( xdc_Ptr oarr, xdc_Int i );

 

extern xdc_Ptr ti_sysbios_knl_Event_Object__first__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Event_Object__next__S( xdc_Ptr obj );

 

extern void ti_sysbios_knl_Event_Params__init__S( xdc_Ptr dst, const void *src, xdc_SizeT psz, xdc_SizeT isz );

 

extern xdc_UInt ti_sysbios_knl_Event_pend__E( ti_sysbios_knl_Event_Handle __inst, xdc_UInt andMask, xdc_UInt orMask, xdc_UInt32 timeout );

 

extern void ti_sysbios_knl_Event_post__E( ti_sysbios_knl_Event_Handle __inst, xdc_UInt eventMask );

 

extern xdc_UInt ti_sysbios_knl_Event_getPostedEvents__E( ti_sysbios_knl_Event_Handle __inst );

 

extern void ti_sysbios_knl_Event_sync__E( ti_sysbios_knl_Event_Handle __inst, xdc_UInt eventId, xdc_UInt count );

 

extern void ti_sysbios_knl_Event_pendTimeout__I( xdc_UArg arg );

 

extern xdc_UInt ti_sysbios_knl_Event_checkEvents__I( ti_sysbios_knl_Event_Object *event, xdc_UInt andMask, xdc_UInt orMask );




 

 

 

 

 
static inline CT__ti_sysbios_knl_Event_Module__id ti_sysbios_knl_Event_Module_id( void ) 
{
    return ti_sysbios_knl_Event_Module__id__C;
}

 
static inline xdc_Bool ti_sysbios_knl_Event_Module_hasMask( void ) 
{
    return ti_sysbios_knl_Event_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 ti_sysbios_knl_Event_Module_getMask( void ) 
{
    return ti_sysbios_knl_Event_Module__diagsMask__C != 0 ? *ti_sysbios_knl_Event_Module__diagsMask__C : 0;
}

 
static inline void ti_sysbios_knl_Event_Module_setMask( xdc_Bits16 mask ) 
{
    if (ti_sysbios_knl_Event_Module__diagsMask__C != 0) *ti_sysbios_knl_Event_Module__diagsMask__C = mask;
}

 
static inline void ti_sysbios_knl_Event_Params_init( ti_sysbios_knl_Event_Params *prms ) 
{
    if (prms) {
        ti_sysbios_knl_Event_Params__init__S(prms, 0, sizeof(ti_sysbios_knl_Event_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 
static inline void ti_sysbios_knl_Event_Params_copy(ti_sysbios_knl_Event_Params *dst, const ti_sysbios_knl_Event_Params *src) 
{
    if (dst) {
        ti_sysbios_knl_Event_Params__init__S(dst, (const void *)src, sizeof(ti_sysbios_knl_Event_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 

 

 
static inline ti_sysbios_knl_Event_Handle ti_sysbios_knl_Event_Object_get(ti_sysbios_knl_Event_Instance_State *oarr, int i) 
{
    return (ti_sysbios_knl_Event_Handle)ti_sysbios_knl_Event_Object__get__S(oarr, i);
}

 
static inline ti_sysbios_knl_Event_Handle ti_sysbios_knl_Event_Object_first( void )
{
    return (ti_sysbios_knl_Event_Handle)ti_sysbios_knl_Event_Object__first__S();
}

 
static inline ti_sysbios_knl_Event_Handle ti_sysbios_knl_Event_Object_next( ti_sysbios_knl_Event_Object *obj )
{
    return (ti_sysbios_knl_Event_Handle)ti_sysbios_knl_Event_Object__next__S(obj);
}

 
static inline xdc_runtime_Types_Label *ti_sysbios_knl_Event_Handle_label( ti_sysbios_knl_Event_Handle inst, xdc_runtime_Types_Label *lab )
{
    return ti_sysbios_knl_Event_Handle__label__S(inst, lab);
}

 
static inline xdc_String ti_sysbios_knl_Event_Handle_name( ti_sysbios_knl_Event_Handle inst )
{
    xdc_runtime_Types_Label lab;
    return ti_sysbios_knl_Event_Handle__label__S(inst, &lab)->iname;
}

 
static inline ti_sysbios_knl_Event_Handle ti_sysbios_knl_Event_handle( ti_sysbios_knl_Event_Struct *str )
{
    return (ti_sysbios_knl_Event_Handle)str;
}

 
static inline ti_sysbios_knl_Event_Struct *ti_sysbios_knl_Event_struct( ti_sysbios_knl_Event_Handle inst )
{
    return (ti_sysbios_knl_Event_Struct*)inst;
}




 































 









 





 





 

 
enum ti_sysbios_knl_Semaphore_Mode {
    ti_sysbios_knl_Semaphore_Mode_COUNTING = 0x0,
    ti_sysbios_knl_Semaphore_Mode_BINARY = 0x1,
    ti_sysbios_knl_Semaphore_Mode_COUNTING_PRIORITY = 0x2,
    ti_sysbios_knl_Semaphore_Mode_BINARY_PRIORITY = 0x3
};
typedef enum ti_sysbios_knl_Semaphore_Mode ti_sysbios_knl_Semaphore_Mode;




 

 
typedef struct ti_sysbios_knl_Semaphore_Args__create {
    xdc_Int count;
} ti_sysbios_knl_Semaphore_Args__create;




 

 
enum ti_sysbios_knl_Semaphore_PendState {
    ti_sysbios_knl_Semaphore_PendState_TIMEOUT = 0,
    ti_sysbios_knl_Semaphore_PendState_POSTED = 1,
    ti_sysbios_knl_Semaphore_PendState_CLOCK_WAIT = 2,
    ti_sysbios_knl_Semaphore_PendState_WAIT_FOREVER = 3
};
typedef enum ti_sysbios_knl_Semaphore_PendState ti_sysbios_knl_Semaphore_PendState;

 
struct ti_sysbios_knl_Semaphore_PendElem {
    ti_sysbios_knl_Task_PendElem tpElem;
    ti_sysbios_knl_Semaphore_PendState pendState;
};




 

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Semaphore_Module__diagsEnabled;
extern  const CT__ti_sysbios_knl_Semaphore_Module__diagsEnabled ti_sysbios_knl_Semaphore_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Semaphore_Module__diagsIncluded;
extern  const CT__ti_sysbios_knl_Semaphore_Module__diagsIncluded ti_sysbios_knl_Semaphore_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__ti_sysbios_knl_Semaphore_Module__diagsMask;
extern  const CT__ti_sysbios_knl_Semaphore_Module__diagsMask ti_sysbios_knl_Semaphore_Module__diagsMask__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Semaphore_Module__gateObj;
extern  const CT__ti_sysbios_knl_Semaphore_Module__gateObj ti_sysbios_knl_Semaphore_Module__gateObj__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Semaphore_Module__gatePrms;
extern  const CT__ti_sysbios_knl_Semaphore_Module__gatePrms ti_sysbios_knl_Semaphore_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__ti_sysbios_knl_Semaphore_Module__id;
extern  const CT__ti_sysbios_knl_Semaphore_Module__id ti_sysbios_knl_Semaphore_Module__id__C;

 
typedef xdc_Bool CT__ti_sysbios_knl_Semaphore_Module__loggerDefined;
extern  const CT__ti_sysbios_knl_Semaphore_Module__loggerDefined ti_sysbios_knl_Semaphore_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Semaphore_Module__loggerObj;
extern  const CT__ti_sysbios_knl_Semaphore_Module__loggerObj ti_sysbios_knl_Semaphore_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__ti_sysbios_knl_Semaphore_Module__loggerFxn0;
extern  const CT__ti_sysbios_knl_Semaphore_Module__loggerFxn0 ti_sysbios_knl_Semaphore_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__ti_sysbios_knl_Semaphore_Module__loggerFxn1;
extern  const CT__ti_sysbios_knl_Semaphore_Module__loggerFxn1 ti_sysbios_knl_Semaphore_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__ti_sysbios_knl_Semaphore_Module__loggerFxn2;
extern  const CT__ti_sysbios_knl_Semaphore_Module__loggerFxn2 ti_sysbios_knl_Semaphore_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__ti_sysbios_knl_Semaphore_Module__loggerFxn4;
extern  const CT__ti_sysbios_knl_Semaphore_Module__loggerFxn4 ti_sysbios_knl_Semaphore_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__ti_sysbios_knl_Semaphore_Module__loggerFxn8;
extern  const CT__ti_sysbios_knl_Semaphore_Module__loggerFxn8 ti_sysbios_knl_Semaphore_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__ti_sysbios_knl_Semaphore_Module__startupDoneFxn)(void);
extern  const CT__ti_sysbios_knl_Semaphore_Module__startupDoneFxn ti_sysbios_knl_Semaphore_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__ti_sysbios_knl_Semaphore_Object__count;
extern  const CT__ti_sysbios_knl_Semaphore_Object__count ti_sysbios_knl_Semaphore_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__ti_sysbios_knl_Semaphore_Object__heap;
extern  const CT__ti_sysbios_knl_Semaphore_Object__heap ti_sysbios_knl_Semaphore_Object__heap__C;

 
typedef xdc_SizeT CT__ti_sysbios_knl_Semaphore_Object__sizeof;
extern  const CT__ti_sysbios_knl_Semaphore_Object__sizeof ti_sysbios_knl_Semaphore_Object__sizeof__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Semaphore_Object__table;
extern  const CT__ti_sysbios_knl_Semaphore_Object__table ti_sysbios_knl_Semaphore_Object__table__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Semaphore_LM_post;
extern  const CT__ti_sysbios_knl_Semaphore_LM_post ti_sysbios_knl_Semaphore_LM_post__C;

 
typedef xdc_runtime_Log_Event CT__ti_sysbios_knl_Semaphore_LM_pend;
extern  const CT__ti_sysbios_knl_Semaphore_LM_pend ti_sysbios_knl_Semaphore_LM_pend__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Semaphore_A_noEvents;
extern  const CT__ti_sysbios_knl_Semaphore_A_noEvents ti_sysbios_knl_Semaphore_A_noEvents__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Semaphore_A_invTimeout;
extern  const CT__ti_sysbios_knl_Semaphore_A_invTimeout ti_sysbios_knl_Semaphore_A_invTimeout__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Semaphore_A_badContext;
extern  const CT__ti_sysbios_knl_Semaphore_A_badContext ti_sysbios_knl_Semaphore_A_badContext__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Semaphore_A_overflow;
extern  const CT__ti_sysbios_knl_Semaphore_A_overflow ti_sysbios_knl_Semaphore_A_overflow__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Semaphore_A_pendTaskDisabled;
extern  const CT__ti_sysbios_knl_Semaphore_A_pendTaskDisabled ti_sysbios_knl_Semaphore_A_pendTaskDisabled__C;

 
typedef xdc_Bool CT__ti_sysbios_knl_Semaphore_supportsEvents;
extern  const CT__ti_sysbios_knl_Semaphore_supportsEvents ti_sysbios_knl_Semaphore_supportsEvents__C;

 
typedef xdc_Bool CT__ti_sysbios_knl_Semaphore_supportsPriority;
extern  const CT__ti_sysbios_knl_Semaphore_supportsPriority ti_sysbios_knl_Semaphore_supportsPriority__C;

 
typedef void (*CT__ti_sysbios_knl_Semaphore_eventPost)(ti_sysbios_knl_Event_Handle, xdc_UInt);
extern  const CT__ti_sysbios_knl_Semaphore_eventPost ti_sysbios_knl_Semaphore_eventPost__C;

 
typedef void (*CT__ti_sysbios_knl_Semaphore_eventSync)(ti_sysbios_knl_Event_Handle, xdc_UInt, xdc_UInt);
extern  const CT__ti_sysbios_knl_Semaphore_eventSync ti_sysbios_knl_Semaphore_eventSync__C;




 

 
struct ti_sysbios_knl_Semaphore_Params {
    size_t __size;
    const void *__self;
    void *__fxns;
    xdc_runtime_IInstance_Params *instance;
    ti_sysbios_knl_Event_Handle event;
    xdc_UInt eventId;
    ti_sysbios_knl_Semaphore_Mode mode;
    xdc_runtime_IInstance_Params __iprms;
};

 
struct ti_sysbios_knl_Semaphore_Struct {
    ti_sysbios_knl_Event_Handle __f0;
    xdc_UInt __f1;
    ti_sysbios_knl_Semaphore_Mode __f2;
    volatile xdc_UInt16 __f3;
    ti_sysbios_knl_Queue_Struct __f4;
    xdc_runtime_Types_CordAddr __name;
};




 

 

 

extern void ti_sysbios_knl_Semaphore_Instance_init__E(ti_sysbios_knl_Semaphore_Object *, xdc_Int count, const ti_sysbios_knl_Semaphore_Params *);

 

extern void ti_sysbios_knl_Semaphore_Instance_finalize__E( ti_sysbios_knl_Semaphore_Object* );

 

extern xdc_runtime_Types_Label *ti_sysbios_knl_Semaphore_Handle__label__S( xdc_Ptr obj, xdc_runtime_Types_Label *lab );

 

extern xdc_Bool ti_sysbios_knl_Semaphore_Module__startupDone__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Semaphore_Object__create__S( xdc_Ptr __oa, xdc_SizeT __osz, xdc_Ptr __aa, const xdc_UChar *__pa, xdc_SizeT __psz, xdc_runtime_Error_Block *__eb );

 

extern ti_sysbios_knl_Semaphore_Handle ti_sysbios_knl_Semaphore_create( xdc_Int count, const ti_sysbios_knl_Semaphore_Params *__prms, xdc_runtime_Error_Block *__eb );

 

extern void ti_sysbios_knl_Semaphore_construct( ti_sysbios_knl_Semaphore_Struct *__obj, xdc_Int count, const ti_sysbios_knl_Semaphore_Params *__prms );

 

extern void ti_sysbios_knl_Semaphore_Object__delete__S( xdc_Ptr instp );

 

extern void ti_sysbios_knl_Semaphore_delete(ti_sysbios_knl_Semaphore_Handle *instp);

 

extern void ti_sysbios_knl_Semaphore_Object__destruct__S( xdc_Ptr objp );

 

extern void ti_sysbios_knl_Semaphore_destruct(ti_sysbios_knl_Semaphore_Struct *obj);

 

extern xdc_Ptr ti_sysbios_knl_Semaphore_Object__get__S( xdc_Ptr oarr, xdc_Int i );

 

extern xdc_Ptr ti_sysbios_knl_Semaphore_Object__first__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Semaphore_Object__next__S( xdc_Ptr obj );

 

extern void ti_sysbios_knl_Semaphore_Params__init__S( xdc_Ptr dst, const void *src, xdc_SizeT psz, xdc_SizeT isz );

 

extern xdc_Int ti_sysbios_knl_Semaphore_getCount__E( ti_sysbios_knl_Semaphore_Handle __inst );

 

extern xdc_Bool ti_sysbios_knl_Semaphore_pend__E( ti_sysbios_knl_Semaphore_Handle __inst, xdc_UInt32 timeout );

 

extern void ti_sysbios_knl_Semaphore_post__E( ti_sysbios_knl_Semaphore_Handle __inst );

 

extern void ti_sysbios_knl_Semaphore_registerEvent__E( ti_sysbios_knl_Semaphore_Handle __inst, ti_sysbios_knl_Event_Handle event, xdc_UInt eventId );

 

extern void ti_sysbios_knl_Semaphore_reset__E( ti_sysbios_knl_Semaphore_Handle __inst, xdc_Int count );

 

extern void ti_sysbios_knl_Semaphore_pendTimeout__I( xdc_UArg arg );




 

 

 

 

 
static inline CT__ti_sysbios_knl_Semaphore_Module__id ti_sysbios_knl_Semaphore_Module_id( void ) 
{
    return ti_sysbios_knl_Semaphore_Module__id__C;
}

 
static inline xdc_Bool ti_sysbios_knl_Semaphore_Module_hasMask( void ) 
{
    return ti_sysbios_knl_Semaphore_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 ti_sysbios_knl_Semaphore_Module_getMask( void ) 
{
    return ti_sysbios_knl_Semaphore_Module__diagsMask__C != 0 ? *ti_sysbios_knl_Semaphore_Module__diagsMask__C : 0;
}

 
static inline void ti_sysbios_knl_Semaphore_Module_setMask( xdc_Bits16 mask ) 
{
    if (ti_sysbios_knl_Semaphore_Module__diagsMask__C != 0) *ti_sysbios_knl_Semaphore_Module__diagsMask__C = mask;
}

 
static inline void ti_sysbios_knl_Semaphore_Params_init( ti_sysbios_knl_Semaphore_Params *prms ) 
{
    if (prms) {
        ti_sysbios_knl_Semaphore_Params__init__S(prms, 0, sizeof(ti_sysbios_knl_Semaphore_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 
static inline void ti_sysbios_knl_Semaphore_Params_copy(ti_sysbios_knl_Semaphore_Params *dst, const ti_sysbios_knl_Semaphore_Params *src) 
{
    if (dst) {
        ti_sysbios_knl_Semaphore_Params__init__S(dst, (const void *)src, sizeof(ti_sysbios_knl_Semaphore_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 

 

 
static inline ti_sysbios_knl_Semaphore_Handle ti_sysbios_knl_Semaphore_Object_get(ti_sysbios_knl_Semaphore_Instance_State *oarr, int i) 
{
    return (ti_sysbios_knl_Semaphore_Handle)ti_sysbios_knl_Semaphore_Object__get__S(oarr, i);
}

 
static inline ti_sysbios_knl_Semaphore_Handle ti_sysbios_knl_Semaphore_Object_first( void )
{
    return (ti_sysbios_knl_Semaphore_Handle)ti_sysbios_knl_Semaphore_Object__first__S();
}

 
static inline ti_sysbios_knl_Semaphore_Handle ti_sysbios_knl_Semaphore_Object_next( ti_sysbios_knl_Semaphore_Object *obj )
{
    return (ti_sysbios_knl_Semaphore_Handle)ti_sysbios_knl_Semaphore_Object__next__S(obj);
}

 
static inline xdc_runtime_Types_Label *ti_sysbios_knl_Semaphore_Handle_label( ti_sysbios_knl_Semaphore_Handle inst, xdc_runtime_Types_Label *lab )
{
    return ti_sysbios_knl_Semaphore_Handle__label__S(inst, lab);
}

 
static inline xdc_String ti_sysbios_knl_Semaphore_Handle_name( ti_sysbios_knl_Semaphore_Handle inst )
{
    xdc_runtime_Types_Label lab;
    return ti_sysbios_knl_Semaphore_Handle__label__S(inst, &lab)->iname;
}

 
static inline ti_sysbios_knl_Semaphore_Handle ti_sysbios_knl_Semaphore_handle( ti_sysbios_knl_Semaphore_Struct *str )
{
    return (ti_sysbios_knl_Semaphore_Handle)str;
}

 
static inline ti_sysbios_knl_Semaphore_Struct *ti_sysbios_knl_Semaphore_struct( ti_sysbios_knl_Semaphore_Handle inst )
{
    return (ti_sysbios_knl_Semaphore_Struct*)inst;
}




 






 





 



 






 

















 




 








 







 















 




 





 




 



 






 














 




 





 



 







 














 




 





 



 






 













 




 





 



 






 
















 




 





 





 






 















 




 





 




 



 






 
















 




 





 





 






 

















 




 





 





 





 

 
struct ti_sysbios_knl_Mailbox_MbxElem {
    ti_sysbios_knl_Queue_Elem elem;
};




 

 
typedef struct ti_sysbios_knl_Mailbox_Args__create {
    xdc_SizeT msgSize;
    xdc_UInt numMsgs;
} ti_sysbios_knl_Mailbox_Args__create;




 

 
typedef xdc_Char __T1_ti_sysbios_knl_Mailbox_Instance_State__allocBuf;
typedef xdc_Char *__ARRAY1_ti_sysbios_knl_Mailbox_Instance_State__allocBuf;
typedef __ARRAY1_ti_sysbios_knl_Mailbox_Instance_State__allocBuf __TA_ti_sysbios_knl_Mailbox_Instance_State__allocBuf;




 

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Mailbox_Module__diagsEnabled;
extern  const CT__ti_sysbios_knl_Mailbox_Module__diagsEnabled ti_sysbios_knl_Mailbox_Module__diagsEnabled__C;

 
typedef xdc_Bits32 CT__ti_sysbios_knl_Mailbox_Module__diagsIncluded;
extern  const CT__ti_sysbios_knl_Mailbox_Module__diagsIncluded ti_sysbios_knl_Mailbox_Module__diagsIncluded__C;

 
typedef xdc_Bits16 *CT__ti_sysbios_knl_Mailbox_Module__diagsMask;
extern  const CT__ti_sysbios_knl_Mailbox_Module__diagsMask ti_sysbios_knl_Mailbox_Module__diagsMask__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Mailbox_Module__gateObj;
extern  const CT__ti_sysbios_knl_Mailbox_Module__gateObj ti_sysbios_knl_Mailbox_Module__gateObj__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Mailbox_Module__gatePrms;
extern  const CT__ti_sysbios_knl_Mailbox_Module__gatePrms ti_sysbios_knl_Mailbox_Module__gatePrms__C;

 
typedef xdc_runtime_Types_ModuleId CT__ti_sysbios_knl_Mailbox_Module__id;
extern  const CT__ti_sysbios_knl_Mailbox_Module__id ti_sysbios_knl_Mailbox_Module__id__C;

 
typedef xdc_Bool CT__ti_sysbios_knl_Mailbox_Module__loggerDefined;
extern  const CT__ti_sysbios_knl_Mailbox_Module__loggerDefined ti_sysbios_knl_Mailbox_Module__loggerDefined__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Mailbox_Module__loggerObj;
extern  const CT__ti_sysbios_knl_Mailbox_Module__loggerObj ti_sysbios_knl_Mailbox_Module__loggerObj__C;

 
typedef xdc_runtime_Types_LoggerFxn0 CT__ti_sysbios_knl_Mailbox_Module__loggerFxn0;
extern  const CT__ti_sysbios_knl_Mailbox_Module__loggerFxn0 ti_sysbios_knl_Mailbox_Module__loggerFxn0__C;

 
typedef xdc_runtime_Types_LoggerFxn1 CT__ti_sysbios_knl_Mailbox_Module__loggerFxn1;
extern  const CT__ti_sysbios_knl_Mailbox_Module__loggerFxn1 ti_sysbios_knl_Mailbox_Module__loggerFxn1__C;

 
typedef xdc_runtime_Types_LoggerFxn2 CT__ti_sysbios_knl_Mailbox_Module__loggerFxn2;
extern  const CT__ti_sysbios_knl_Mailbox_Module__loggerFxn2 ti_sysbios_knl_Mailbox_Module__loggerFxn2__C;

 
typedef xdc_runtime_Types_LoggerFxn4 CT__ti_sysbios_knl_Mailbox_Module__loggerFxn4;
extern  const CT__ti_sysbios_knl_Mailbox_Module__loggerFxn4 ti_sysbios_knl_Mailbox_Module__loggerFxn4__C;

 
typedef xdc_runtime_Types_LoggerFxn8 CT__ti_sysbios_knl_Mailbox_Module__loggerFxn8;
extern  const CT__ti_sysbios_knl_Mailbox_Module__loggerFxn8 ti_sysbios_knl_Mailbox_Module__loggerFxn8__C;

 
typedef xdc_Bool (*CT__ti_sysbios_knl_Mailbox_Module__startupDoneFxn)(void);
extern  const CT__ti_sysbios_knl_Mailbox_Module__startupDoneFxn ti_sysbios_knl_Mailbox_Module__startupDoneFxn__C;

 
typedef xdc_Int CT__ti_sysbios_knl_Mailbox_Object__count;
extern  const CT__ti_sysbios_knl_Mailbox_Object__count ti_sysbios_knl_Mailbox_Object__count__C;

 
typedef xdc_runtime_IHeap_Handle CT__ti_sysbios_knl_Mailbox_Object__heap;
extern  const CT__ti_sysbios_knl_Mailbox_Object__heap ti_sysbios_knl_Mailbox_Object__heap__C;

 
typedef xdc_SizeT CT__ti_sysbios_knl_Mailbox_Object__sizeof;
extern  const CT__ti_sysbios_knl_Mailbox_Object__sizeof ti_sysbios_knl_Mailbox_Object__sizeof__C;

 
typedef xdc_Ptr CT__ti_sysbios_knl_Mailbox_Object__table;
extern  const CT__ti_sysbios_knl_Mailbox_Object__table ti_sysbios_knl_Mailbox_Object__table__C;

 
typedef xdc_runtime_Assert_Id CT__ti_sysbios_knl_Mailbox_A_invalidBufSize;
extern  const CT__ti_sysbios_knl_Mailbox_A_invalidBufSize ti_sysbios_knl_Mailbox_A_invalidBufSize__C;

 
typedef xdc_UInt CT__ti_sysbios_knl_Mailbox_maxTypeAlign;
extern  const CT__ti_sysbios_knl_Mailbox_maxTypeAlign ti_sysbios_knl_Mailbox_maxTypeAlign__C;




 

 
struct ti_sysbios_knl_Mailbox_Params {
    size_t __size;
    const void *__self;
    void *__fxns;
    xdc_runtime_IInstance_Params *instance;
    xdc_runtime_IHeap_Handle heap;
    ti_sysbios_knl_Event_Handle readerEvent;
    xdc_UInt readerEventId;
    ti_sysbios_knl_Event_Handle writerEvent;
    xdc_UInt writerEventId;
    xdc_Ptr buf;
    xdc_UInt bufSize;
    xdc_runtime_IInstance_Params __iprms;
};

 
struct ti_sysbios_knl_Mailbox_Struct {
    xdc_runtime_IHeap_Handle __f0;
    xdc_SizeT __f1;
    xdc_UInt __f2;
    xdc_Ptr __f3;
    xdc_UInt __f4;
    __TA_ti_sysbios_knl_Mailbox_Instance_State__allocBuf __f5;
    ti_sysbios_knl_Queue_Struct __f6;
    ti_sysbios_knl_Queue_Struct __f7;
    ti_sysbios_knl_Semaphore_Struct __f8;
    ti_sysbios_knl_Semaphore_Struct __f9;
    xdc_runtime_Types_CordAddr __name;
};




 

 

extern xdc_Int ti_sysbios_knl_Mailbox_Module_startup__E( xdc_Int state );

extern xdc_Int ti_sysbios_knl_Mailbox_Module_startup__F( xdc_Int state );

 

extern xdc_Int ti_sysbios_knl_Mailbox_Instance_init__E(ti_sysbios_knl_Mailbox_Object *, xdc_SizeT msgSize, xdc_UInt numMsgs, const ti_sysbios_knl_Mailbox_Params *, xdc_runtime_Error_Block *);

 

extern void ti_sysbios_knl_Mailbox_Instance_finalize__E( ti_sysbios_knl_Mailbox_Object* , int );

 

extern xdc_runtime_Types_Label *ti_sysbios_knl_Mailbox_Handle__label__S( xdc_Ptr obj, xdc_runtime_Types_Label *lab );

 

extern xdc_Bool ti_sysbios_knl_Mailbox_Module__startupDone__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Mailbox_Object__create__S( xdc_Ptr __oa, xdc_SizeT __osz, xdc_Ptr __aa, const xdc_UChar *__pa, xdc_SizeT __psz, xdc_runtime_Error_Block *__eb );

 

extern ti_sysbios_knl_Mailbox_Handle ti_sysbios_knl_Mailbox_create( xdc_SizeT msgSize, xdc_UInt numMsgs, const ti_sysbios_knl_Mailbox_Params *__prms, xdc_runtime_Error_Block *__eb );

 

extern void ti_sysbios_knl_Mailbox_construct( ti_sysbios_knl_Mailbox_Struct *__obj, xdc_SizeT msgSize, xdc_UInt numMsgs, const ti_sysbios_knl_Mailbox_Params *__prms, xdc_runtime_Error_Block *__eb );

 

extern void ti_sysbios_knl_Mailbox_Object__delete__S( xdc_Ptr instp );

 

extern void ti_sysbios_knl_Mailbox_delete(ti_sysbios_knl_Mailbox_Handle *instp);

 

extern void ti_sysbios_knl_Mailbox_Object__destruct__S( xdc_Ptr objp );

 

extern void ti_sysbios_knl_Mailbox_destruct(ti_sysbios_knl_Mailbox_Struct *obj);

 

extern xdc_Ptr ti_sysbios_knl_Mailbox_Object__get__S( xdc_Ptr oarr, xdc_Int i );

 

extern xdc_Ptr ti_sysbios_knl_Mailbox_Object__first__S( void );

 

extern xdc_Ptr ti_sysbios_knl_Mailbox_Object__next__S( xdc_Ptr obj );

 

extern void ti_sysbios_knl_Mailbox_Params__init__S( xdc_Ptr dst, const void *src, xdc_SizeT psz, xdc_SizeT isz );

 

extern xdc_SizeT ti_sysbios_knl_Mailbox_getMsgSize__E( ti_sysbios_knl_Mailbox_Handle __inst );

 

extern xdc_Int ti_sysbios_knl_Mailbox_getNumFreeMsgs__E( ti_sysbios_knl_Mailbox_Handle __inst );

 

extern xdc_Int ti_sysbios_knl_Mailbox_getNumPendingMsgs__E( ti_sysbios_knl_Mailbox_Handle __inst );

 

extern xdc_Bool ti_sysbios_knl_Mailbox_pend__E( ti_sysbios_knl_Mailbox_Handle __inst, xdc_Ptr msg, xdc_UInt32 timeout );

 

extern xdc_Bool ti_sysbios_knl_Mailbox_post__E( ti_sysbios_knl_Mailbox_Handle __inst, xdc_Ptr msg, xdc_UInt32 timeout );

 

extern void ti_sysbios_knl_Mailbox_cleanQue__I( ti_sysbios_knl_Queue_Handle obj );

 

extern xdc_Int ti_sysbios_knl_Mailbox_postInit__I( ti_sysbios_knl_Mailbox_Object *obj, xdc_SizeT blockSize );




 

 

 

 

 
static inline CT__ti_sysbios_knl_Mailbox_Module__id ti_sysbios_knl_Mailbox_Module_id( void ) 
{
    return ti_sysbios_knl_Mailbox_Module__id__C;
}

 
static inline xdc_Bool ti_sysbios_knl_Mailbox_Module_hasMask( void ) 
{
    return ti_sysbios_knl_Mailbox_Module__diagsMask__C != 0;
}

 
static inline xdc_Bits16 ti_sysbios_knl_Mailbox_Module_getMask( void ) 
{
    return ti_sysbios_knl_Mailbox_Module__diagsMask__C != 0 ? *ti_sysbios_knl_Mailbox_Module__diagsMask__C : 0;
}

 
static inline void ti_sysbios_knl_Mailbox_Module_setMask( xdc_Bits16 mask ) 
{
    if (ti_sysbios_knl_Mailbox_Module__diagsMask__C != 0) *ti_sysbios_knl_Mailbox_Module__diagsMask__C = mask;
}

 
static inline void ti_sysbios_knl_Mailbox_Params_init( ti_sysbios_knl_Mailbox_Params *prms ) 
{
    if (prms) {
        ti_sysbios_knl_Mailbox_Params__init__S(prms, 0, sizeof(ti_sysbios_knl_Mailbox_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 
static inline void ti_sysbios_knl_Mailbox_Params_copy(ti_sysbios_knl_Mailbox_Params *dst, const ti_sysbios_knl_Mailbox_Params *src) 
{
    if (dst) {
        ti_sysbios_knl_Mailbox_Params__init__S(dst, (const void *)src, sizeof(ti_sysbios_knl_Mailbox_Params), sizeof(xdc_runtime_IInstance_Params));
    }
}

 

 

 
static inline ti_sysbios_knl_Mailbox_Handle ti_sysbios_knl_Mailbox_Object_get(ti_sysbios_knl_Mailbox_Instance_State *oarr, int i) 
{
    return (ti_sysbios_knl_Mailbox_Handle)ti_sysbios_knl_Mailbox_Object__get__S(oarr, i);
}

 
static inline ti_sysbios_knl_Mailbox_Handle ti_sysbios_knl_Mailbox_Object_first( void )
{
    return (ti_sysbios_knl_Mailbox_Handle)ti_sysbios_knl_Mailbox_Object__first__S();
}

 
static inline ti_sysbios_knl_Mailbox_Handle ti_sysbios_knl_Mailbox_Object_next( ti_sysbios_knl_Mailbox_Object *obj )
{
    return (ti_sysbios_knl_Mailbox_Handle)ti_sysbios_knl_Mailbox_Object__next__S(obj);
}

 
static inline xdc_runtime_Types_Label *ti_sysbios_knl_Mailbox_Handle_label( ti_sysbios_knl_Mailbox_Handle inst, xdc_runtime_Types_Label *lab )
{
    return ti_sysbios_knl_Mailbox_Handle__label__S(inst, lab);
}

 
static inline xdc_String ti_sysbios_knl_Mailbox_Handle_name( ti_sysbios_knl_Mailbox_Handle inst )
{
    xdc_runtime_Types_Label lab;
    return ti_sysbios_knl_Mailbox_Handle__label__S(inst, &lab)->iname;
}

 
static inline ti_sysbios_knl_Mailbox_Handle ti_sysbios_knl_Mailbox_handle( ti_sysbios_knl_Mailbox_Struct *str )
{
    return (ti_sysbios_knl_Mailbox_Handle)str;
}

 
static inline ti_sysbios_knl_Mailbox_Struct *ti_sysbios_knl_Mailbox_struct( ti_sysbios_knl_Mailbox_Handle inst )
{
    return (ti_sysbios_knl_Mailbox_Struct*)inst;
}




 






 





 



 




enum CommandTypes
{
	COMMAND_UNKNOWN = 0x00,
	COMMAND_HORIZONTAL_RANGE = 0x02,
	COMMAND_TRIGGER_MODE = 0x03,
	COMMAND_TRIGGER_TYPE = 0x04,
	COMMAND_TRIGGER_THRESHOLD = 0x05,
	COMMAND_SAMPLE_LENGTH = 0x07,
	COMMAND_TRIGGER_STATE = 0x08,
	COMMAND_TRIGGER_ARM = 0x0A,
	COMMAND_TRIGGER_FORCE = 0x0F,

	COMMAND_NUM_SAMPLES = 0x71,

	COMMAND_VERTICAL_RANGE_A = 0xA1,
	COMMAND_DC_OFFSET_A = 0xA6,
	COMMAND_COUPLING_A = 0xAC,
	SAMPLE_PACKET_A_8 = 0xAD,
	SAMPLE_PACKET_A_12 = 0xAE,

	COMMAND_VERTICAL_RANGE_B = 0xB1,
	COMMAND_DC_OFFSET_B = 0xB6,
	COMMAND_COUPLING_B = 0xBC,
	SAMPLE_PACKET_B_8 = 0xBD,
	SAMPLE_PACKET_B_12 = 0xBE,

	COMMAND_DISPLAY_POWER = 0xD0,
	COMMAND_DIPLAY_BRIGHTNESS = 0xD1,

	COMMAND_KEEPALIVE = 0xEE,

	COMMAND_FUNCTION_GEN_OUT = 0xF0,
	COMMAND_FUNCTION_GEN_WAVE = 0xF1,
	COMMAND_FUNCTION_GEN_VOLTAGE = 0xF2,
	COMMAND_FUNCTION_GEN_OFFSET = 0xF3,
	COMMAND_FUNCTION_GEN_FREQUENCY = 0xF4,

	_COMMAND_REPAINT = 0x88,
	_COMMAND_IP_UPDATE = 0x80,
	_COMMAND_CONN_UPDATE = 0x81,
	_COMMAND_PTR_UP = 0x82,
	_COMMAND_PTR_DOWN = 0x83,
	_COMMAND_TRIGGER_INDICATOR = 0x90,
	_COMMAND_ACQUISITION_SEND_COMPLETE = 0x91,
	_COMMAND_OVERVOLTAGE = 0x99,
};


typedef struct __attribute__((__packed__)) Command
{
	uint8_t type;
	uint8_t is_confirmation;
	int32_t args[2];
} Command;

typedef struct __attribute__((__packed__)) SampleCommand
{
	uint8_t type;
	uint8_t seq_num;
	int16_t num_samples;
	int16_t period;
	void* buffer;
} SampleCommand;


enum menus
{
	MAIN_MENU,
	RANGE_MENU,
	TRIGGER_ARM_MENU,
	TRIGGER_THRESHOLD_MENU,
	WAVEGEN1_MENU,
	WAVEGEN2_MENU,
	BRIGHTNESS_MENU,
	OVERVOLTAGE_MENU
};

extern uint8_t current_menu;
extern tCanvasWidget menus[];
extern void OnMAIN(tWidget *psWidget);
extern void OnOVERVOLTAGE(tWidget *psWidget);
extern void OnTRIGGER_ARM(tWidget *psWidget);
extern void OnTRIGGER_THRESHOLD(tWidget *psWidget);
extern void OnWAVEGEN1(tWidget *psWidget);
extern void OnWAVEGEN2(tWidget *psWidget);

extern void UISend(Command *cmd, uint32_t timeout);

extern uint32_t Standard_Step(uint32_t, int8_t);
extern void SI_Print(char* line1, char* line2, int32_t val, char* suffix, char* prefixes);
extern void SI_Micro_Print(char* line1, char* line2, int32_t val, char* suffix);
extern void Repaint(tWidget *psWidget);

extern void Init_UI(void);






 

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 



#pragma diag_push
#pragma CHECK_MISRA("-6.3")  
#pragma CHECK_MISRA("-19.1")  
#pragma CHECK_MISRA("-20.1")  
#pragma CHECK_MISRA("-20.2")  

 



#pragma diag_push
#pragma CHECK_MISRA("-19.4")  


#pragma diag_pop

static __inline size_t  strlen(const char *string);

static __inline char *strcpy(char *dest, const char *src);
static __inline char *strncpy(char *dest, const char *src, size_t n);
static __inline char *strcat(char *string1, const char *string2);
static __inline char *strncat(char *dest, const char *src, size_t n);
static __inline char *strchr(const char *string, int c);
static __inline char *strrchr(const char *string, int c);

static __inline int  strcmp(const char *string1, const char *string2);
static __inline int  strncmp(const char *string1, const char *string2, size_t n);

 int     strcoll(const char *string1, const char *_string2);
 size_t  strxfrm(char *to, const char *from, size_t n);
 char   *strpbrk(const char *string, const char *chs);
 size_t  strspn(const char *string, const char *chs);
 size_t  strcspn(const char *string, const char *chs);
 char   *strstr(const char *string1, const char *string2);
 char   *strtok(char *str1, const char *str2);
 char   *strerror(int _errno);
 char   *strdup(const char *string);


 void   *memmove(void *s1, const void *s2, size_t n);
#pragma diag_push
#pragma CHECK_MISRA("-16.4")  
 void   *memcpy(void *s1, const void *s2, size_t n);
#pragma diag_pop

static __inline int     memcmp(const void *cs, const void *ct, size_t n);
static __inline void   *memchr(const void *cs, int c, size_t n);

 void   *memset(void *mem, int ch, size_t length);






#pragma diag_push
#pragma CHECK_MISRA("-19.4")  


#pragma diag_pop

#pragma diag_push  


 


 

#pragma CHECK_MISRA("-5.7")  
#pragma CHECK_MISRA("-6.1")  
#pragma CHECK_MISRA("-8.5")  
#pragma CHECK_MISRA("-10.1")  
#pragma CHECK_MISRA("-10.3")  
#pragma CHECK_MISRA("-11.5")  
#pragma CHECK_MISRA("-12.1")  
#pragma CHECK_MISRA("-12.2")  
#pragma CHECK_MISRA("-12.4")  
#pragma CHECK_MISRA("-12.5")  
#pragma CHECK_MISRA("-12.6")  
#pragma CHECK_MISRA("-12.13")  
#pragma CHECK_MISRA("-13.1")  
#pragma CHECK_MISRA("-14.7")  
#pragma CHECK_MISRA("-14.8")  
#pragma CHECK_MISRA("-14.9")  
#pragma CHECK_MISRA("-17.4")  
#pragma CHECK_MISRA("-17.6")  

static __inline size_t strlen(const char *string)
{
   size_t      n = (size_t)-1;
   const char *s = string;

   do n++; while (*s++);
   return n;
}

static __inline char *strcpy(register char *dest, register const char *src)
{
     register char       *d = dest;     
     register const char *s = src;

     while (*d++ = *s++);
     return dest;
}

static __inline char *strncpy(register char *dest,
		     register const char *src,
		     register size_t n)
{
     if (n) 
     {
	 register char       *d = dest;
	 register const char *s = src;
	 while ((*d++ = *s++) && --n);               
	 if (n-- > 1) do *d++ = '\0'; while (--n);   
     }
     return dest;
}

static __inline char *strcat(char *string1, const char *string2)
{
   char       *s1 = string1;
   const char *s2 = string2;

   while (*s1) s1++;		      
   while (*s1++ = *s2++);	      
   return string1;
}

static __inline char *strncat(char *dest, const char *src, register size_t n)
{
    if (n)
    {
	char       *d = dest;
	const char *s = src;

	while (*d) d++;                       

	while (n--)
	  if (!(*d++ = *s++)) return dest;  
	*d = 0;
    }
    return dest;
}

static __inline char *strchr(const char *string, int c)
{
   char        tch, ch  = c;
   const char *s        = string;

   for (;;)
   {
       if ((tch = *s) == ch) return (char *) s;
       if (!tch)             return (char *) 0;
       s++;
   }
}

static __inline char *strrchr(const char *string, int c)
{
   char        tch, ch = c;
   char       *result  = 0;
   const char *s       = string;

   for (;;)
   {
      if ((tch = *s) == ch) result = (char *) s;
      if (!tch) break;
      s++;
   }

   return result;
}

static __inline int strcmp(register const char *string1,
		  register const char *string2)
{
   register int c1, res;

   for (;;)
   {
       c1  = (unsigned char)*string1++;
       res = c1 - (unsigned char)*string2++;

       if (c1 == 0 || res != 0) break;
   }

   return res;
}

static __inline int strncmp(const char *string1, const char *string2, size_t n)
{
     if (n) 
     {
	 const char *s1 = string1;
	 const char *s2 = string2;
	 unsigned char cp;
	 int         result;

	 do 
	    if (result = (unsigned char)*s1++ - (cp = (unsigned char)*s2++))
                return result;
	 while (cp && --n);
     }
     return 0;
}

static __inline int memcmp(const void *cs, const void *ct, size_t n)
{
   if (n) 
   {
       const unsigned char *mem1 = (unsigned char *)cs;
       const unsigned char *mem2 = (unsigned char *)ct;
       int                 cp1, cp2;

       while ((cp1 = *mem1++) == (cp2 = *mem2++) && --n);
       return cp1 - cp2;
   }
   return 0;
}

static __inline void *memchr(const void *cs, int c, size_t n)
{
   if (n)
   {
      const unsigned char *mem = (unsigned char *)cs;   
      unsigned char        ch  = c;

      do 
         if ( *mem == ch ) return (void *)mem;
         else mem++;
      while (--n);
   }
   return 0;
}





#pragma diag_pop


#pragma diag_push


 
 
#pragma CHECK_MISRA("-19.15")


#pragma diag_pop






 















 




 





 





 







































































                                            
                                            








































extern void ADCIntRegister(uint32_t ui32Base, uint32_t ui32SequenceNum,
                           void (*pfnHandler)(void));
extern void ADCIntUnregister(uint32_t ui32Base, uint32_t ui32SequenceNum);
extern void ADCIntDisable(uint32_t ui32Base, uint32_t ui32SequenceNum);
extern void ADCIntEnable(uint32_t ui32Base, uint32_t ui32SequenceNum);
extern uint32_t ADCIntStatus(uint32_t ui32Base, uint32_t ui32SequenceNum,
                             _Bool bMasked);
extern void ADCIntClear(uint32_t ui32Base, uint32_t ui32SequenceNum);
extern void ADCSequenceEnable(uint32_t ui32Base, uint32_t ui32SequenceNum);
extern void ADCSequenceDisable(uint32_t ui32Base, uint32_t ui32SequenceNum);
extern void ADCSequenceConfigure(uint32_t ui32Base, uint32_t ui32SequenceNum,
                                 uint32_t ui32Trigger, uint32_t ui32Priority);
extern void ADCSequenceStepConfigure(uint32_t ui32Base,
                                     uint32_t ui32SequenceNum,
                                     uint32_t ui32Step, uint32_t ui32Config);
extern int32_t ADCSequenceOverflow(uint32_t ui32Base,
                                   uint32_t ui32SequenceNum);
extern void ADCSequenceOverflowClear(uint32_t ui32Base,
                                     uint32_t ui32SequenceNum);
extern int32_t ADCSequenceUnderflow(uint32_t ui32Base,
                                    uint32_t ui32SequenceNum);
extern void ADCSequenceUnderflowClear(uint32_t ui32Base,
                                      uint32_t ui32SequenceNum);
extern int32_t ADCSequenceDataGet(uint32_t ui32Base, uint32_t ui32SequenceNum,
                                  uint32_t *pui32Buffer);
extern void ADCProcessorTrigger(uint32_t ui32Base, uint32_t ui32SequenceNum);
extern void ADCSoftwareOversampleConfigure(uint32_t ui32Base,
                                           uint32_t ui32SequenceNum,
                                           uint32_t ui32Factor);
extern void ADCSoftwareOversampleStepConfigure(uint32_t ui32Base,
                                               uint32_t ui32SequenceNum,
                                               uint32_t ui32Step,
                                               uint32_t ui32Config);
extern void ADCSoftwareOversampleDataGet(uint32_t ui32Base,
                                         uint32_t ui32SequenceNum,
                                         uint32_t *pui32Buffer,
                                         uint32_t ui32Count);
extern void ADCHardwareOversampleConfigure(uint32_t ui32Base,
                                           uint32_t ui32Factor);
extern void ADCClockConfigSet(uint32_t ui32Base, uint32_t ui32Config,
                              uint32_t ui32ClockDiv);
extern uint32_t ADCClockConfigGet(uint32_t ui32Base, uint32_t *pui32ClockDiv);

extern void ADCComparatorConfigure(uint32_t ui32Base, uint32_t ui32Comp,
                                   uint32_t ui32Config);
extern void ADCComparatorRegionSet(uint32_t ui32Base, uint32_t ui32Comp,
                                   uint32_t ui32LowRef, uint32_t ui32HighRef);
extern void ADCComparatorReset(uint32_t ui32Base, uint32_t ui32Comp,
                               _Bool bTrigger, _Bool bInterrupt);
extern void ADCComparatorIntDisable(uint32_t ui32Base,
                                    uint32_t ui32SequenceNum);
extern void ADCComparatorIntEnable(uint32_t ui32Base,
                                   uint32_t ui32SequenceNum);
extern uint32_t ADCComparatorIntStatus(uint32_t ui32Base);
extern void ADCComparatorIntClear(uint32_t ui32Base, uint32_t ui32Status);
extern void ADCIntDisableEx(uint32_t ui32Base, uint32_t ui32IntFlags);
extern void ADCIntEnableEx(uint32_t ui32Base, uint32_t ui32IntFlags);
extern uint32_t ADCIntStatusEx(uint32_t ui32Base, _Bool bMasked);
extern void ADCIntClearEx(uint32_t ui32Base, uint32_t ui32IntFlags);
extern void ADCSequenceDMAEnable(uint32_t ui32Base, uint32_t ui32SequenceNum);
extern void ADCSequenceDMADisable(uint32_t ui32Base, uint32_t ui32SequenceNum);
extern _Bool ADCBusy(uint32_t ui32Base);
extern void ADCReferenceSet(uint32_t ui32Base, uint32_t ui32Ref);
extern uint32_t ADCReferenceGet(uint32_t ui32Base);
extern void ADCPhaseDelaySet(uint32_t ui32Base, uint32_t ui32Phase);
extern uint32_t ADCPhaseDelayGet(uint32_t ui32Base);
extern void ADCSampleRateSet(uint32_t ui32Base, uint32_t ui32ADCClock,
                             uint32_t ui32Rate);
extern uint32_t ADCSampleRateGet(uint32_t ui32Base);






















































































                                            


















































                                            
                                            











































extern uint32_t SysCtlSRAMSizeGet(void);
extern uint32_t SysCtlFlashSizeGet(void);
extern uint32_t SysCtlFlashSectorSizeGet(void);
extern _Bool SysCtlPeripheralPresent(uint32_t ui32Peripheral);
extern _Bool SysCtlPeripheralReady(uint32_t ui32Peripheral);
extern void SysCtlPeripheralPowerOn(uint32_t ui32Peripheral);
extern void SysCtlPeripheralPowerOff(uint32_t ui32Peripheral);
extern void SysCtlPeripheralReset(uint32_t ui32Peripheral);
extern void SysCtlPeripheralEnable(uint32_t ui32Peripheral);
extern void SysCtlPeripheralDisable(uint32_t ui32Peripheral);
extern void SysCtlPeripheralSleepEnable(uint32_t ui32Peripheral);
extern void SysCtlPeripheralSleepDisable(uint32_t ui32Peripheral);
extern void SysCtlPeripheralDeepSleepEnable(uint32_t ui32Peripheral);
extern void SysCtlPeripheralDeepSleepDisable(uint32_t ui32Peripheral);
extern void SysCtlPeripheralClockGating(_Bool bEnable);
extern void SysCtlIntRegister(void (*pfnHandler)(void));
extern void SysCtlIntUnregister(void);
extern void SysCtlIntEnable(uint32_t ui32Ints);
extern void SysCtlIntDisable(uint32_t ui32Ints);
extern void SysCtlIntClear(uint32_t ui32Ints);
extern uint32_t SysCtlIntStatus(_Bool bMasked);
extern void SysCtlLDOSleepSet(uint32_t ui32Voltage);
extern uint32_t SysCtlLDOSleepGet(void);
extern void SysCtlLDODeepSleepSet(uint32_t ui32Voltage);
extern uint32_t SysCtlLDODeepSleepGet(void);
extern void SysCtlSleepPowerSet(uint32_t ui32Config);
extern void SysCtlDeepSleepPowerSet(uint32_t ui32Config);
extern void SysCtlReset(void);
extern void SysCtlSleep(void);
extern void SysCtlDeepSleep(void);
extern uint32_t SysCtlResetCauseGet(void);
extern void SysCtlResetCauseClear(uint32_t ui32Causes);
extern void SysCtlBrownOutConfigSet(uint32_t ui32Config,
                                    uint32_t ui32Delay);
extern void SysCtlDelay(uint32_t ui32Count);
extern void SysCtlMOSCConfigSet(uint32_t ui32Config);
extern uint32_t SysCtlPIOSCCalibrate(uint32_t ui32Type);
extern void SysCtlClockSet(uint32_t ui32Config);
extern uint32_t SysCtlClockGet(void);
extern void SysCtlDeepSleepClockSet(uint32_t ui32Config);
extern void SysCtlDeepSleepClockConfigSet(uint32_t ui32Div,
                                          uint32_t ui32Config);
extern void SysCtlPWMClockSet(uint32_t ui32Config);
extern uint32_t SysCtlPWMClockGet(void);
extern void SysCtlIOSCVerificationSet(_Bool bEnable);
extern void SysCtlMOSCVerificationSet(_Bool bEnable);
extern void SysCtlPLLVerificationSet(_Bool bEnable);
extern void SysCtlClkVerificationClear(void);
extern void SysCtlGPIOAHBEnable(uint32_t ui32GPIOPeripheral);
extern void SysCtlGPIOAHBDisable(uint32_t ui32GPIOPeripheral);
extern void SysCtlUSBPLLEnable(void);
extern void SysCtlUSBPLLDisable(void);
extern uint32_t SysCtlClockFreqSet(uint32_t ui32Config,
                                   uint32_t ui32SysClock);
extern void SysCtlResetBehaviorSet(uint32_t ui32Behavior);
extern uint32_t SysCtlResetBehaviorGet(void);
extern void SysCtlClockOutConfig(uint32_t ui32Config, uint32_t ui32Div);
extern void SysCtlAltClkConfig(uint32_t ui32Config);
extern uint32_t SysCtlNMIStatus(void);
extern void SysCtlNMIClear(uint32_t ui32Status);
extern void SysCtlVoltageEventConfig(uint32_t ui32Config);
extern uint32_t SysCtlVoltageEventStatus(void);
extern void SysCtlVoltageEventClear(uint32_t ui32Status);




































































typedef struct
{
    
    
    
    volatile void *pvSrcEndAddr;

    
    
    
    volatile void *pvDstEndAddr;

    
    
    
    volatile uint32_t ui32Control;

    
    
    
    volatile uint32_t ui32Spare;
}
tDMAControlTable;










































































































































































































































































extern void uDMAEnable(void);
extern void uDMADisable(void);
extern uint32_t uDMAErrorStatusGet(void);
extern void uDMAErrorStatusClear(void);
extern void uDMAChannelEnable(uint32_t ui32ChannelNum);
extern void uDMAChannelDisable(uint32_t ui32ChannelNum);
extern _Bool uDMAChannelIsEnabled(uint32_t ui32ChannelNum);
extern void uDMAControlBaseSet(void *pControlTable);
extern void *uDMAControlBaseGet(void);
extern void *uDMAControlAlternateBaseGet(void);
extern void uDMAChannelRequest(uint32_t ui32ChannelNum);
extern void uDMAChannelAttributeEnable(uint32_t ui32ChannelNum,
                                       uint32_t ui32Attr);
extern void uDMAChannelAttributeDisable(uint32_t ui32ChannelNum,
                                        uint32_t ui32Attr);
extern uint32_t uDMAChannelAttributeGet(uint32_t ui32ChannelNum);
extern void uDMAChannelControlSet(uint32_t ui32ChannelStructIndex,
                                  uint32_t ui32Control);
extern void uDMAChannelTransferSet(uint32_t ui32ChannelStructIndex,
                                   uint32_t ui32Mode, void *pvSrcAddr,
                                   void *pvDstAddr, uint32_t ui32TransferSize);
extern void uDMAChannelScatterGatherSet(uint32_t ui32ChannelNum,
                                        uint32_t ui32TaskCount,
                                        void *pvTaskList,
                                        uint32_t ui32IsPeriphSG);
extern uint32_t uDMAChannelSizeGet(uint32_t ui32ChannelStructIndex);
extern uint32_t uDMAChannelModeGet(uint32_t ui32ChannelStructIndex);
extern void uDMAIntRegister(uint32_t ui32IntChannel, void (*pfnHandler)(void));
extern void uDMAIntUnregister(uint32_t ui32IntChannel);
extern uint32_t uDMAIntStatus(void);
extern void uDMAIntClear(uint32_t ui32ChanMask);
extern void uDMAChannelAssign(uint32_t ui32Mapping);















extern void uDMAChannelSelectDefault(uint32_t ui32DefPeriphs);
extern void uDMAChannelSelectSecondary(uint32_t ui32SecPeriphs);





















































                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            












                                            






                                            
                                            
                                            
                                            






                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
























                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            






























                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            






                                            
                                            
                                            












                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            


















                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            






                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            






                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            






                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            












                                            
                                            
                                            
                                            


















                                            
                                            
                                            
                                            






                                            
                                            
                                            
                                            






                                            
                                            
                                            
                                            






                                            
                                            
                                            
                                            












                                            
                                            
                                            
                                            


















                                            
                                            
                                            
                                            






                                            
                                            
                                            
                                            






                                            
                                            
                                            
                                            






                                            
                                            
                                            
                                            






























                                            






                                            






                                            






                                            












































































































                                            
                                            






                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            





















































                                            
                                            
                                            
                                            






                                            


















                                            
























































































                                            
                                            




































                                            
                                            


















                                            















































                                            
                                            
                                            
                                            
                                            




extern void ADC_Init(void);
extern uint32_t ADCGetPeriod(void);
extern void ADCSetFreq(uint32_t freq);

extern uint16_t adc_buffer_A_PRI[1024] __attribute__(( aligned(8) ));
extern uint16_t adc_buffer_A_ALT[1024] __attribute__(( aligned(8) ));
extern uint16_t adc_buffer_B_PRI[1024] __attribute__(( aligned(8) ));
extern uint16_t adc_buffer_B_ALT[1024] __attribute__(( aligned(8) ));






 






 















 




 





 





 







 
















 




 





 





 



 






 

















 




 





 





 








 


typedef enum Channel
{
	CHANNEL_A = 0,
	CHANNEL_B = 1
} Channel;

typedef enum FrontendCoupling
{
	COUPLING_DC = 0,
	COUPLING_AC = 1
} FrontendCoupling;

extern void FrontEnd_Init(void);
extern uint32_t FrontEndGetHorDiv(void);
extern void FrontEndSetHorDiv(uint32_t us);
extern uint32_t FrontEndGetVerDiv(Channel channel);
extern void FrontEndSetVerDiv(Channel channel, uint32_t uV);
extern FrontendCoupling FrontEndGetCoupling(Channel channel);
extern void FrontEndSetCoupling(Channel channel, FrontendCoupling coupling);
extern void FrontEndNotify(void);





typedef enum TriggerMode
{
	TRIGGER_MODE_AUTO = 0,
	TRIGGER_MODE_SINGLE = 1,
	TRIGGER_MODE_NORMAL = 2
} TriggerMode;

typedef enum TriggerType
{
	TRIGGER_TYPE_LEVEL = 0,
	TRIGGER_TYPE_RISING = 1,
	TRIGGER_TYPE_FALLING = 2
} TriggerType;

typedef enum TriggerState
{
	TRIGGER_STATE_ARMED = 0,
	TRIGGER_STATE_TRIGGERED = 1,
	TRIGGER_STATE_STOP = 2
} TriggerState;

typedef enum SampleSize
{
	SAMPLE_SIZE_8_BIT = 0,
	SAMPLE_SIZE_12_BIT = 1,
} SampleSize;

extern ti_sysbios_knl_Event_Handle AcqEvent;
extern ti_sysbios_knl_Semaphore_Handle bufferlock;

extern void Trigger_Init(void);
extern void ForceTrigger(void);
extern int32_t TriggerGetThreshold(void);
extern void TriggerSetThreshold(int32_t threshold);
extern TriggerMode TriggerGetMode(void);
extern void TriggerSetMode(TriggerMode mode);
extern TriggerType TriggerGetType(void);
extern void TriggerSetType(TriggerType type);
extern Channel TriggerGetChannel(void);
extern void TriggerSetChannel(Channel channel);
extern SampleSize TriggerGetSampleSize(void);
extern void TriggerSetSampleSize(SampleSize mode);
extern TriggerState TriggerGetState(void);
extern void TriggerSetState(TriggerState state);
extern uint32_t TriggerGetNumSamples(void);
extern void TriggerSetNumSamples(uint32_t newNum);
extern uint16_t TriggerGetSampleDivisor(void);
extern void TriggerSetSampleDivisor(uint16_t divisor);
extern void TriggerNotify(void);


static void OnTriggerType(tWidget *psWidget);
static void OnTriggerMode(tWidget *psWidget);
static void OnTriggerForce(tWidget *psWidget);
static void OnTriggerArm(tWidget *psWidget);
static void OnTriggerThresholdIncrease(tWidget *psWidget);
static void OnTriggerThresholdDecrease(tWidget *psWidget);
static void OnTriggerChannel(tWidget *psWidget);

static tPushButtonWidget TriggerChannel = { { sizeof(tPushButtonWidget), (tWidget *)(&menus[TRIGGER_ARM_MENU]), (tWidget *)(0), (tWidget *)(0), &SSD1289_Display, { 220, 120, (220) + (100) - 1, (120) + (80) - 1 }, RectangularButtonMsgProc }, 0x00000002 | 0x00000004 | 0x00000001, 0x000000FF, 0x00FFFF00, 0x00FFFFFF, 0x00FFFFFF, &g_sFontCm18b, 0, 0, 0, 0, 0, 0, OnTriggerChannel };
static tPushButtonWidget TriggerModeButton = { { sizeof(tPushButtonWidget), (tWidget *)(&menus[TRIGGER_ARM_MENU]), (tWidget *)(&TriggerChannel), (tWidget *)(0), &SSD1289_Display, { 110, 120, (110) + (100) - 1, (120) + (80) - 1 }, RectangularButtonMsgProc }, 0x00000002 | 0x00000004 | 0x00000001, 0x000000FF, 0x00FFFF00, 0x00FFFFFF, 0x00FFFFFF, &g_sFontCm18b, 0, 0, 0, 0, 0, 0, OnTriggerMode };
static tPushButtonWidget TriggerToThreshold = { { sizeof(tPushButtonWidget), (tWidget *)(&menus[TRIGGER_ARM_MENU]), (tWidget *)(&TriggerModeButton), (tWidget *)(0), &SSD1289_Display, { 0, 120, (0) + (100) - 1, (120) + (80) - 1 }, RectangularButtonMsgProc }, 0x00000002 | 0x00000004 | 0x00000001 | 0x00000080, 0x00808080, 0x00FFFF00, 0x00FFFFFF, 0x00FFFFFF, &g_sFontCm18b, "More", 0, 0, 0, 0, 0, OnTRIGGER_THRESHOLD };
static tPushButtonWidget TriggerForce = { { sizeof(tPushButtonWidget), (tWidget *)(&menus[TRIGGER_ARM_MENU]), (tWidget *)(&TriggerToThreshold), (tWidget *)(0), &SSD1289_Display, { 220, 30, (220) + (100) - 1, (30) + (80) - 1 }, RectangularButtonMsgProc }, 0x00000002 | 0x00000004 | 0x00000001, 0x00FF0000, 0x0090EE90, 0x00FFFFFF, 0x00FFFFFF, &g_sFontCm18b, "Force", 0, 0, 0, 0, 0, OnTriggerForce };
static tPushButtonWidget TriggerArm = { { sizeof(tPushButtonWidget), (tWidget *)(&menus[TRIGGER_ARM_MENU]), (tWidget *)(&TriggerForce), (tWidget *)(0), &SSD1289_Display, { 110, 30, (110) + (100) - 1, (30) + (80) - 1 }, RectangularButtonMsgProc }, 0x00000002 | 0x00000004 | 0x00000001, 0x00FF0000, 0x00FFFF00, 0x00FFFFFF, 0x00FFFFFF, &g_sFontCm18b, "Arm", 0, 0, 0, 0, 0, OnTriggerArm };
tPushButtonWidget TriggerArmBack = { { sizeof(tPushButtonWidget), (tWidget *)(&menus[TRIGGER_ARM_MENU]), (tWidget *)(&TriggerArm), (tWidget *)(0), &SSD1289_Display, { 0, 30, (0) + (100) - 1, (30) + (80) - 1 }, RectangularButtonMsgProc }, 0x00000002 | 0x00000004 | 0x00000001 | 0x00000080, 0x00808080, 0x00FFFF00, 0x00FFFFFF, 0x00FFFFFF, &g_sFontCm18b, "Back", 0, 0, 0, 0, 0, OnMAIN };

static tCanvasWidget TriggerThresholdLabel1 = { { sizeof(tCanvasWidget), (tWidget *)(&menus[TRIGGER_THRESHOLD_MENU]), (tWidget *)(0), (tWidget *)(0), &SSD1289_Display, { 170, 50, (170) + (90) - 1, (50) + (20) - 1 }, CanvasMsgProc }, 0x00000004 | 0x00000020 | 0x00000002, 0, 0, 0x00FFFFFF, &g_sFontCm20, 0, 0, 0 };
static tCanvasWidget TriggerThresholdLabel2 = { { sizeof(tCanvasWidget), (tWidget *)(&menus[TRIGGER_THRESHOLD_MENU]), (tWidget *)(&TriggerThresholdLabel1), (tWidget *)(0), &SSD1289_Display, { 170, 70, (170) + (90) - 1, (70) + (20) - 1 }, CanvasMsgProc }, 0x00000004 | 0x00000020 | 0x00000002, 0, 0, 0x00FFFFFF, &g_sFontCm20, 0, 0, 0 };
static tPushButtonWidget TriggerTypeButton = { { sizeof(tPushButtonWidget), (tWidget *)(&menus[TRIGGER_THRESHOLD_MENU]), (tWidget *)(&TriggerThresholdLabel2), (tWidget *)(0), &SSD1289_Display, { 110, 120, (110) + (100) - 1, (120) + (80) - 1 }, RectangularButtonMsgProc }, 0x00000002 | 0x00000004 | 0x00000001, 0x000000FF, 0x00FFFF00, 0x00FFFFFF, 0x00FFFFFF, &g_sFontCm18b, 0, 0, 0, 0, 0, 0, OnTriggerType };
static tPushButtonWidget TriggerToArm = { { sizeof(tPushButtonWidget), (tWidget *)(&menus[TRIGGER_THRESHOLD_MENU]), (tWidget *)(&TriggerTypeButton), (tWidget *)(0), &SSD1289_Display, { 0, 120, (0) + (100) - 1, (120) + (80) - 1 }, RectangularButtonMsgProc }, 0x00000002 | 0x00000004 | 0x00000001 | 0x00000080, 0x00808080, 0x00FFFF00, 0x00FFFFFF, 0x00FFFFFF, &g_sFontCm18b, "More", 0, 0, 0, 0, 0, OnTRIGGER_ARM };
static tPushButtonWidget TriggerThresholdDecrease = { { sizeof(tPushButtonWidget), (tWidget *)(&menus[TRIGGER_THRESHOLD_MENU]), (tWidget *)(&TriggerToArm), (tWidget *)(0), &SSD1289_Display, { 110, 30, (110) + (60) - 1, (30) + (80) - 1 }, RectangularButtonMsgProc }, 0x00000002 | 0x00000004 | 0x00000001, 0x000000FF, 0x00FFFF00, 0x00FFFFFF, 0x00FFFFFF, &g_sFontCm18b, "-", 0, 0, 0, 0, 0, OnTriggerThresholdDecrease };
static tPushButtonWidget TriggerThresholdIncrease = { { sizeof(tPushButtonWidget), (tWidget *)(&menus[TRIGGER_THRESHOLD_MENU]), (tWidget *)(&TriggerThresholdDecrease), (tWidget *)(0), &SSD1289_Display, { 260, 30, (260) + (60) - 1, (30) + (80) - 1 }, RectangularButtonMsgProc }, 0x00000002 | 0x00000004 | 0x00000001, 0x000000FF, 0x00FFFF00, 0x00FFFFFF, 0x00FFFFFF, &g_sFontCm18b, "+", 0, 0, 0, 0, 0, OnTriggerThresholdIncrease };
tPushButtonWidget TriggerThresholdBack = { { sizeof(tPushButtonWidget), (tWidget *)(&menus[TRIGGER_THRESHOLD_MENU]), (tWidget *)(&TriggerThresholdIncrease), (tWidget *)(0), &SSD1289_Display, { 0, 30, (0) + (100) - 1, (30) + (80) - 1 }, RectangularButtonMsgProc }, 0x00000002 | 0x00000004 | 0x00000001 | 0x00000080, 0x00808080, 0x00FFFF00, 0x00FFFFFF, 0x00FFFFFF, &g_sFontCm18b, "Back", 0, 0, 0, 0, 0, OnMAIN };

static void
OnTriggerType(tWidget *psWidget)
{
	TriggerSetType((TriggerType)((TriggerGetType() + 1) % 3));
}

static void
OnTriggerMode(tWidget *psWidget)
{
	TriggerSetMode((TriggerMode)((TriggerGetMode() + 1) % 3));
}

static void
OnTriggerForce(tWidget *psWidget)
{
	ForceTrigger();
}

static void
OnTriggerArm(tWidget *psWidget)
{
	if (TriggerGetState() == TRIGGER_STATE_STOP)
	{
		TriggerSetState(TRIGGER_STATE_ARMED);
	}
}

static void
OnTriggerThresholdIncrease(tWidget *psWidget)
{
	TriggerSetThreshold(TriggerGetThreshold() + 100000);
}

static void
OnTriggerThresholdDecrease(tWidget *psWidget)
{
	TriggerSetThreshold(TriggerGetThreshold() - 100000);
}

static void
OnTriggerChannel(tWidget *psWidget)
{
	TriggerSetChannel((Channel)!TriggerGetChannel());
}

void
TriggerSetThresholdLevelText(const char* line1, const char* line2)
{
	do { tCanvasWidget *pW = &TriggerThresholdLabel1; const char *pcT = line1; pW->pcText = pcT; } while(0);
	do { tCanvasWidget *pW = &TriggerThresholdLabel2; const char *pcT = line2; pW->pcText = pcT; } while(0);

	if (current_menu == TRIGGER_THRESHOLD_MENU)
	{
		Repaint((tWidget *)&TriggerThresholdLabel1);
		Repaint((tWidget *)&TriggerThresholdLabel2);
	}
}

void
TriggerSetModeText(const char* text)
{
	do { tPushButtonWidget *psW = &TriggerModeButton; const char *pcT = text; psW->pcText = pcT; } while(0);

	if (current_menu == TRIGGER_ARM_MENU)
	{
		Repaint((tWidget *)&TriggerModeButton);
	}
}

void
TriggerSetTypeText(const char* text)
{
	do { tPushButtonWidget *psW = &TriggerTypeButton; const char *pcT = text; psW->pcText = pcT; } while(0);

	if (current_menu == TRIGGER_THRESHOLD_MENU)
	{
		Repaint((tWidget *)&TriggerTypeButton);
	}
}

void
TriggerSetChannelText(const char* text)
{
	do { tPushButtonWidget *psW = &TriggerChannel; const char *pcT = text; psW->pcText = pcT; } while(0);

	if (current_menu == TRIGGER_ARM_MENU)
	{
		Repaint((tWidget *)&TriggerChannel);
	}
}
