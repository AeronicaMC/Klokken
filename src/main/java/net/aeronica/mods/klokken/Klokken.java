/* MIT License
 *
 * Copyright (c) 2017 Aeronica
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.aeronica.mods.klokken;

import net.aeronica.mods.klokken.server.ServerProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Klokken.MODID, name = Klokken.MODNAME, version = Klokken.VERSION,
     acceptedMinecraftVersions = "[1.12,1.13)",
     dependencies = Klokken.DEPS, updateJSON = Klokken.UPDATE,
     certificateFingerprint = "999640c365a8443393a1a21df2c0ede9488400e9")

public class Klokken
{
    public static final String MODID = "klokken";
    public static final String MODNAME = "Klokken";
    public static final String VERSION = "{@VERSION}";
    public static final String DEPS = "required-after:forge@[1.9.4-12.17.0.2051,)";
    public static final String UPDATE = ""; //"https://gist.githubusercontent.com/Aeronica/dbc2619e0011d5bdbe7a162d0c6aa82b/raw/klokken_update.json";
    
    @Mod.Instance(MODID)
    public static Klokken instance;

    @SidedProxy(clientSide = "net.aeronica.mods.klokken.client.ClientProxy", serverSide = "net.aeronica.mods.klokken.server.ServerProxy")
    public static ServerProxy proxy;

    public static final CreativeTabs TAB_KLOKKEN = new ModTab();
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModLogger.setLogger(event.getModLog());
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
        // NetworkRegistry.INSTANCE.registerGuiHandler(instance, GUIHandler.getInstance());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }

    @Mod.EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
        System.out.printf("*** [Klokken] Invalid fingerprint detected! ***\n");
    }
    
    public static String prependModID(String name)
    {
        return MODID + ":" + name;
    }
}