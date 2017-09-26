package zz.com.hanhan.testdisklrucache.custom;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by lenovo on 2017/1/9.
 */

public class SimpleGlideModule implements GlideModule {
    @Override
    public void applyOptions(final Context context, GlideBuilder builder) {
//      第一种设置内部的缓存路径，或者不写这行代码，Glide会自动缓存到data/data/<packname>/~~
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,
//                1024 * 1024 * 30));


//      第二，如果要指定缓存目录时，Glide就不会缓存？？？
        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                File cacheDirName = new File(context.getExternalCacheDir(), "zz_cache_dir_name");
                cacheDirName.mkdirs();
                return DiskLruCacheWrapper.get(cacheDirName, 30*1024*1024);
            }
        });

////        设置磁盘缓存
//        File cacheDirName = context.getExternalCacheDir();
//        int diskCacheSize = 1024 * 1024 * 30;//最多可以缓存多少字节的数据
//        builder.setDiskCache(new DiskLruCacheFactory(cacheDirName.getPath(), "zzglide", diskCacheSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
