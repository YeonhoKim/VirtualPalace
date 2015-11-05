using UnityEngine;
using MyScript.Interface;
using BridgeApi.Controller;

namespace MyScript.States
{
    //ARScene 평상시 모드
    public class ARSceneIdleState : AbstractGazeInputState, ISceneChangeState
    {
        public ARSceneIdleState(StateManager managerRef) : base(managerRef, "ARSceneIdleState")
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
        }

        protected override void HandleCancelOperation()
        {
            ReturnToLobbyScene();
        }
        private void ReturnToLobbyScene()
        {
            StateManager.SwitchScene(UnityScene.Lobby);
        }

        protected override void HandleOtherOperation(Operation operation)
        {
            //base.HandleOtherOperation(operation);
            switch (operation.Type)
            {
                case Operation.AR_RENDERING:
                    ARrenderItem item = JsonInterpreter.ParseARrenderItem(operation);
                    Debug.Log("====== AR item : " + item);
                    break;

                default:
                    return;
            }

        }
    }
}

