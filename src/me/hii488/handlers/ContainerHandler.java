package me.hii488.handlers;

import java.util.HashMap;

import me.hii488.objects.containers.BaseContainer;
import me.hii488.objects.containers.RenderContainer;
import me.hii488.objects.entities.BaseEntity;
import me.hii488.registries.EntityRegistry;

public class ContainerHandler {
	
	public static String currentContainerIndentifier;
	public static HashMap<String, BaseContainer> containers = new HashMap<String, BaseContainer>();
	private static RenderContainer renderContainer = new RenderContainer();
	
	public static BaseContainer getLoadedContainer(){
		return containers.get(currentContainerIndentifier);
	}
	
	public static BaseContainer getContainer(String identifier){
		return containers.get(identifier);
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
		if(getLoadedContainer() != null) unloadCurrentContainer();
		currentContainerIndentifier = identifier;
		containers.get(currentContainerIndentifier).addEntity(EntityRegistry.player);
		containers.get(currentContainerIndentifier).onLoad();
		containers.get(currentContainerIndentifier).loaded = true;
		
		renderContainer = getLoadedContainer().createRenderContainer();
	}
	
	public static void unloadCurrentContainer() {
		containers.get(currentContainerIndentifier).onUnload();
		containers.get(currentContainerIndentifier).loaded = false;
		containers.get(currentContainerIndentifier).getEntities().remove(EntityRegistry.player);
	}
	
	public static RenderContainer getRenderContainer(){
		return renderContainer;
	}
	
	public static void updateRenderContainer(){
		try{
			renderContainer.getEntities().clear();
			for(BaseEntity e : getLoadedContainer().getEntities()){
				BaseEntity e2 = e.createRenderEntity();
				if(e2 != null) renderContainer.getEntities().add(e2);
			}
		}catch(Exception e){e.printStackTrace();}
		
		renderContainer.grid = getLoadedContainer().grid.clone(); // TODO: Change this so only the to be rendered tiles are cloned?
	}
	
}
