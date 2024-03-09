/**
 * This is an implementation of Bob Jenkins' hash. It can produce both 32-bit
 * and 64-bit hash values. Converted from Aaron C# sources to Java
 * Taken from https://github.com/NFSTools/Aaron/blob/master/Aaron/Utils/Hashing.cs
 *
 * @author heyitsleo
 */
public class JenkinsHash {

    public int hash(String k)
    {
    	int koffs = 0;
    	int len = k.length();
    	int a = 0x9e3779b9;
    	int b = a;
    	int c = 0xABCDEF00;

    	while (len >= 12)
    	{
    		a += (int)k.charAt(0 + koffs) + ((int)k.charAt(1 + koffs) << 8) + ((int)k.charAt(2 + koffs) << 16) + ((int)k.charAt(3 + koffs) << 24);
    		b += (int)k.charAt(4 + koffs) + ((int)k.charAt(5 + koffs) << 8) + ((int)k.charAt(6 + koffs) << 16) + ((int)k.charAt(7 + koffs) << 24);
    		c += (int)k.charAt(8 + koffs) + ((int)k.charAt(9 + koffs) << 8) + ((int)k.charAt(10 + koffs) << 16) + ((int)k.charAt(11 + koffs) << 24);

    		a -= b;
    		a -= c;
    		a ^= (c >>> 13);
    		b -= c;
    		b -= a;
    		b ^= (a << 8);
    		c -= a;
    		c -= b;
    		c ^= (b >>> 13);
    		a -= b;
    		a -= c;
    		a ^= (c >>> 12);
    		b -= c;
    		b -= a;
    		b ^= (a << 16);
    		c -= a;
    		c -= b;
    		c ^= (b >>> 5);
    		a -= b;
    		a -= c;
    		a ^= (c >>> 3);
    		b -= c;
    		b -= a;
    		b ^= (a << 10);
    		c -= a;
    		c -= b;
    		c ^= (b >>> 15);

    		koffs += 12;
    		len -= 12;
    	}

    	c += (int)k.length();

    	switch (len)
    	{
    	case 11:
    		c += (int)k.charAt(10 + koffs) << 24;
    	case 10:
    		c += (int)k.charAt(9 + koffs) << 16;
    	case 9:
    		c += (int)k.charAt(8 + koffs) << 8;
    	case 8:
    		b += (int)k.charAt(7 + koffs) << 24;
    	case 7:
    		b += (int)k.charAt(6 + koffs) << 16;
    	case 6:
    		b += (int)k.charAt(5 + koffs) << 8;
    	case 5:
    		b += (int)k.charAt(4 + koffs);
    	case 4:
    		a += (int)k.charAt(3 + koffs) << 24;
    	case 3:
    		a += (int)k.charAt(2 + koffs) << 16;
    	case 2:
    		a += (int)k.charAt(1 + koffs) << 8;
    	case 1:
    		a += (int)k.charAt(0 + koffs);
    		break;
    	}

    	a -= b;
    	a -= c;
    	a ^= (c >>> 13);
    	b -= c;
    	b -= a;
    	b ^= (a << 8);
    	c -= a;
    	c -= b;
    	c ^= (b >>> 13);
    	a -= b;
    	a -= c;
    	a ^= (c >>> 12);
    	b -= c;
    	b -= a;
    	b ^= (a << 16);
    	c -= a;
    	c -= b;
    	c ^= (b >>> 5);
    	a -= b;
    	a -= c;
    	a ^= (c >>> 3);
    	b -= c;
    	b -= a;
    	b ^= (a << 10);
    	c -= a;
    	c -= b;
    	c ^= (b >>> 15);

    	return c;
    }

}