package org.ong.android.examples.hikvision.debug;

import java.lang.reflect.Field;

public class DebugTools {
	public static void dump(Object target) {
		StringBuilder out = new StringBuilder();
		out.append( ">>>DUMP>>> [" ).append( target.getClass() ).append( "\r\n" );
		boolean accessible = false;
		try {
			for ( Field entry : target.getClass().getDeclaredFields() ) {
				accessible = entry.isAccessible();
				if ( ! accessible ) 
					entry.setAccessible( true );
				
				out.append( "  " );
				out.append( entry.getName() ).append( " : " )
						.append( entry.get( target ) );
				out.append( "\r\n" );
				
				if ( ! accessible ) entry.setAccessible( false );				
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			out.append( "] <<<DUMP<<<" );
			System.out.println( out.toString() );
			out = null;
		}
	}

	public static void dump(Object target, boolean recursion) {
		throw new UnsupportedOperationException();
	}
}