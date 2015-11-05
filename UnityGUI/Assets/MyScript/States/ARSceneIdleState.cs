using UnityEngine;
using MyScript.Interface;

namespace MyScript.States
{
	//ARScene 평상시 모드
	public class ARSceneIdleState : AbstractGazeInputState,ISceneChangeState
	{
		private GameObject ARScreenObj;
		public ARSceneIdleState (StateManager managerRef) :base(managerRef , "ARSceneIdleState")
		{

		}

		public UnityScene UnitySceneID { get { return UnityScene.AR; } }

		public void OnSceneChanged()
		{
			Debug.Log("=============== " + Name + " : Scene changed");
			Init();
		}

		protected override void Init()
		{
			base.Init();
			// 스크린의 게임오브젝트를 가져온다
			ARScreenObj = GameObject.Find ("ARView");
		}

		protected override void HandleCancelOperation() 
		{
			ReturnToLobbyScene ();
		}
		private void ReturnToLobbyScene()
		{
			StateManager.SwitchScene(UnityScene.Lobby);
		}
	}
}

