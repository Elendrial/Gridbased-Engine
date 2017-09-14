package me.hii488.handlers;

import java.util.HashMap;

import me.hii488.objects.containers.BaseContainer;
import me.hii488.registries.EntityRegistry;

public class ContainerHandler {
	
	public static String currentContainerIndentifier;
	public static HashMap<String, BaseContainer> containers = new HashMap<String, BaseContainer>();
	
	public static BaseContainer getLoadedContainer(){
		return containers.get(currentContainerIndentifier);
	}
	
	public static void addContainer(BaseContainer container) {
		if(!containers.containsKey(container.identifier))
			containers.put(container.identifier, container);
	}
	
	public static void loadNewContainer(BaseContainer container) {
		if(!containers.containsKey(container.identifier))
			addContainer(container);
		
		loadNewContainer(container.identifier);
	}
	
	public static void loadNewContainer(String identifier) {
		unloadCurrentContainer();
		currentContainerIndentifier = identifier;
		containers.get(currentContainerIndentifier).addEntity(EntityRegistry.player);
		containers.get(currentContainerIndentifier).onLoad();
	}
	
	public static void unloadCurrentContainer() {
		containers.get(currentContainerIndentifier).onUnload();
		containers.get(currentContainerIndentifier).loaded = false;
		containers.get(currentContainerIndentifier).getEntities().remove(EntityRegistry.player);
	}
	
}
