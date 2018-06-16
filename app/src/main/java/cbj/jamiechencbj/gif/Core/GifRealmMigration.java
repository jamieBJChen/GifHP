package cbj.jamiechencbj.gif.Core;

import cbj.jamiechencbj.gif.Utils.GifLogger;
import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class GifRealmMigration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        // DynamicRealm exposes an editable schema
        RealmSchema schema = realm.getSchema();
        GifLogger.d("Old Schema version: "+Long.toString(oldVersion));
        GifLogger.d("New Schema version: "+Long.toString(newVersion));
    }

}
