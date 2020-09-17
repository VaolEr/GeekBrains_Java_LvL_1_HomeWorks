package Lesson8;

import java.util.Objects;

public class CRC {
    private final String    CRC_name;           // name of CRC function
    private final int       CRC_Width;          // Width of CRC: 8, 16, 32 or 64 bit
    private final long      CRC_Poly;           // CRC polynomial
    private final long      CRC_Poly_Reverse;   // CRC polynomial reverse value
    private final long      CRC_Init;           // Init CRC value
    private final boolean   CRC_RefIn;          // CRC Reflected In flag
    private final boolean   CRC_RefOut;         // CRC Reflected Out flag
    private final long      CRC_XorOut;         // CRC Xor out value
    //private long    CRC_Check;          // CRC check value

    public long[]  CRC8_Table       = new long[256];  // table for count the CRC-8
    public long[]  CRC8_R_Table     = new long[256];  // table for count the CRC-8
    public long[]  CRC16_Table      = new long[256]; // table for count the CRC-16
    public long[]  CRC16_R_Table    = new long[256]; // table for count the CRC-16
    public long[]  CRC32_Table      = new long[256];  // table for count the CRC-32
    public long[]  CRC32_R_Table    = new long[256];  // table for count the CRC-32
    public long[]  CRC64Table       = new long[256];  // table for count the CRC-64
    public long[]  CRCTable         = new long[256];  // table for count the CRC

    public CRC(String crc_name, int crc_width, long crc_poly, long crc_init, boolean crc_refin, boolean crc_refout, long crc_xorout)
    {
        this.CRC_name = crc_name;

        if (crc_width != 8 && crc_width !=16 && crc_width != 32 && crc_width != 64) throw new IllegalArgumentException("Object \"" + CRC_name + "\" : CRC_width value " + crc_width + " is out of list: 8,16,32,64"); //check this for correct work!!!!
        else this.CRC_Width = crc_width;

        this.CRC_Poly = crc_poly;
        this.CRC_Init = crc_init;
        this.CRC_RefIn = crc_refin;
        this.CRC_RefOut = crc_refout;
        this.CRC_XorOut = crc_xorout;

        this.CRC_Poly_Reverse = Reverse(this.CRC_Poly, this.CRC_Width);

        if (this.CRC_Width == 8)
        {
            //Init_CRC_Table();  // this table for count the CRC checksumm
            Init_CRC8_Table(); // this table for show for user;
        }
        else if (this.CRC_Width == 16)
        {
            //To DO
            //Init_CRC_Table();  // this table for count the CRC checksumm
            Init_CRC16_Table(); // this table for show for user;
        }
        else if (this.CRC_Width == 32)
        {
            //To DO
            //Init_CRC_Table();  // this table for count the CRC checksumm
            Init_CRC32_Table(); // this table for show for user;
        }
        else if (this.CRC_Width == 64)
        {
            //To DO
            //Init_CRC_Table();  // this table for count the CRC checksumm
            //Init_CRC32_Table(); // this table for show for user;
        }
    }

    public String getCRC_name() {
        return this.CRC_name;
    }

    public int getCRC_Width() {
        return this.CRC_Width;
    }

    public long getCRC_Poly() {
        return this.CRC_Poly;
    }

    public long getCRC_Poly_Reverse() {
        return this.CRC_Poly_Reverse;
    }

    public long getCRC_Init() {
        return this.CRC_Init;
    }

    public boolean getCRC_RefIn() {
        return this.CRC_RefIn;
    }

    public boolean getCRC_RefOut() {
        return this.CRC_RefOut;
    }

    public long getCRC_XorOut() {
        return this.CRC_XorOut;
    }

    public static long Reverse(long data, int crcwidth)
    {
        long revers_data = 0;

        byte[] array = new byte[crcwidth];
        for(byte i = 0; i < crcwidth; i ++)
        {
            int power2 =  1 << i;
            if ((data & power2) == power2) array[i] = 1;
            else array[i] = 0;
        }

        for (int i = crcwidth-1; i >= 0; i--)
        {
            revers_data = revers_data | (array[i] << (crcwidth - 1 - i));
        }
        return revers_data;
    }

    public void Init_CRC8_Table()
    {
        long Value = 0;
        long Mask = ((1 << this.CRC_Width) - 1);
        long TopBit = (1 << (this.CRC_Width - 1));
        if(!this.CRC_RefIn) {
            for (long i = 0; i < 256; i++) {
                Value = i << (this.CRC_Width - 8);

                for (int j = 0; j < 8; j++) {
                    if ((Value & TopBit) == TopBit) {
                        Value = (Value << 1) ^ this.CRC_Poly;
                    } else
                        Value <<= 1;

                    Value &= Mask;

                }
                this.CRC8_Table[(int) i] = Value;
            }
        }
        if(this.CRC_RefIn) {
            for (long i = 0; i < 256; i++) {

                Value = i;

                for (int j = 8; j > 0; j--) {
                    if ((Value & 1) == 1) {
                        Value = (Value >> 1) ^ this.CRC_Poly_Reverse;
                    } else
                        Value >>= 1;
                }
                this.CRC8_R_Table[(int)Reverse(i,8)] = Reverse(Value, this.CRC_Width);
            }
        }

    }

    public long CRC8Count(int[] buff, int bufflen)
    {
        long data;
        long crc = this.CRC_Init;
        for (int i = 0; i < bufflen; i++) {
            if(!this.CRC_RefIn) crc = CRC8_Table[(int) (crc ^ buff[i])];
            if(this.CRC_RefIn){
                data = Reverse(buff[i], 8) ^ crc;
                crc = CRC8_R_Table[(int)data];
            }
        }
        crc = crc ^ this.CRC_XorOut;
        if(this.CRC_RefOut)
        {
            crc = Reverse(crc, this.CRC_Width);
        }
        return crc; // return the crc
    }

    public void Init_CRC16_Table()
    {
        long Value = 0;
        long Mask = ((1 << this.CRC_Width) - 1);
        long TopBit = (1 << (this.CRC_Width - 1));
        if(!this.CRC_RefIn) {
            for (long i = 0; i < 256; i++) {

                Value = i << (this.CRC_Width - 8);

                for (int j = 0; j < 8; j++) {
                    if ((Value & TopBit) == TopBit) {
                        Value = (Value << 1) ^ this.CRC_Poly;
                    } else
                        Value <<= 1;

                    Value &= Mask;

                }
                this.CRC16_Table[(int) i] = Value;
            }
        }
        if(this.CRC_RefIn) {
            for (long i = 0; i < 256; i++) {

                Value = i;

                for (int j = 8; j > 0; j--) {
                    if ((Value & 1) == 1) {
                        Value = (Value >> 1) ^ this.CRC_Poly_Reverse;
                    } else
                        Value >>= 1;
                }
                this.CRC16_R_Table[(int)Reverse(i,8)] = Reverse(Value, this.CRC_Width);
            }
        }
    }

    public long CRC16Count(int[] buff, int bufflen)
    {
        long data;
        long crc = this.CRC_Init;
        for (int i = 0; i < bufflen; i++) {
            if(!this.CRC_RefIn) crc = (crc<<8) ^ CRC16_Table[(int) ((crc>>8) & 0xFF ^ buff[i])];
            if(this.CRC_RefIn){
                data = Reverse(buff[i], 8)^(crc>>8);
                crc = CRC16_R_Table[(int)data] ^ (int)(0xFFFF & (crc << 8));
            }
        }
        crc = crc ^ this.CRC_XorOut;
        if(this.CRC_RefOut)
        {
            crc = Reverse(crc, this.CRC_Width);
        }
        return crc; // return the crc
    }

    public void Init_CRC32_Table()
    {
        long Value = 0;
        long Mask = ((1 << this.CRC_Width) - 1);
        long TopBit = (long) (1 << ((long) this.CRC_Width - 1L));
        if(!this.CRC_RefIn) {
            for (long i = 0; i < 256; i++) {

                Value = (i << (this.CRC_Width - 8)) & 0xFFFF_FFFFL;

                for (int j = 0; j < 8; j++) {
                    if((Value & 2_0000_0000_0000L) == 2_0000_0000_0000L){
                        //if ((Value & TopBit) == TopBit) {
                        Value = ((Value << 1) ^ this.CRC_Poly) & 0xFFFF_FFFFL;
                    } else
                        Value <<= 1;

                    Value &= Mask;

                }
                this.CRC32_Table[(int) i] = Value;
            }
        }
        if(this.CRC_RefIn) {
            for (long i = 0; i < 256; i++) {

                Value = i & 0xFFFF_FFFFL;

                for (int j = 8; j > 0; j--) {
                    if ((Value & 1) == 1) {
                        Value = ((Value >> 1) ^ this.CRC_Poly_Reverse) & 0xFFFF_FFFFL;
                        Value = Value;
                    } else
                        Value = (Value >> 1) & 0xFFFF_FFFFL;
                    Value = Value & 0xFFFF_FFFFL;
                }
                this.CRC32_R_Table[(int)Reverse(i,8)] = Reverse(Value, this.CRC_Width) & 0xFFFF_FFFFL;
            }
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) return true;
        if(obj == null || getClass()!= obj.getClass()) return false;
        CRC crc = (CRC) obj;
        return this.CRC_name == crc.getCRC_name() &&
                this.CRC_Width == crc.getCRC_Width() &&
                this.CRC_Poly == crc.getCRC_Poly() &&
                this.CRC_RefIn == crc.getCRC_RefIn() &&
                this.CRC_RefOut == crc.getCRC_RefOut() &&
                this.CRC_XorOut == crc.getCRC_XorOut();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.CRC_name,this.CRC_Width,this.CRC_Poly,this.CRC_RefIn,this.CRC_RefOut,this.CRC_XorOut);
    }

    @Override
    public String toString(){
        return "CRC algorithm: " + this.CRC_name + ".\n" +
                "Parametrs: Width - " + this.CRC_Width +
                "; Polynomial - 0x" + Long.toHexString(this.CRC_Poly) +
                "; Reflected Input - " + Boolean.toString(this.CRC_RefIn).toUpperCase() +
                "; Reflected Output - " + Boolean.toString(this.CRC_RefOut).toUpperCase() +
                "; XOR output - 0x" + Long.toHexString(this.CRC_XorOut) + ".";
    }
}
